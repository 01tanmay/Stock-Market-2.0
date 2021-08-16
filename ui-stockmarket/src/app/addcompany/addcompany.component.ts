import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Company } from '../model/Company';
import { ErrorMessage } from '../model/ErrorMessage';
import { StockExchange } from '../model/StockExchange';

@Component({
  selector: 'app-addcompany',
  templateUrl: './addcompany.component.html',
  styleUrls: ['./addcompany.component.css']
})
export class AddcompanyComponent implements OnInit {

  form: FormGroup;
  company = new Company();
  companyResponse: Company;
  addCompanyDetailsFlag: boolean = false;
  errorMsg: ErrorMessage;
  errorFlag: boolean = false;

  constructor(public fb: FormBuilder, public http: HttpClient) {
    this.form = this.fb.group({
      companyName: [''],
      companyCode: [''],
      companyCeo: [''],
      companyTurnover: [''],
      companyWebsite: [''],
      stockPrice: ['']
    })
  }

  ngOnInit(): void {
  }

  submitForm() {
    console.log(this.form.value);

    this.addCompanyDetails().subscribe(
      (response) => {
        console.log(response);
        this.companyResponse = response;
        this.addCompanyDetailsFlag = true;
        this.errorFlag = false;
      },
      (error) => {
        this.errorMsg = error.error;
        this.addCompanyDetailsFlag = false;
        this.errorFlag = true;
      }
    )
  }

  addCompanyDetails() : Observable<Company> {

    this.company.companyCode = this.form.get('companyCode').value;
    this.company.companyCeo = this.form.get('companyCeo').value;
    this.company.companyName = this.form.get('companyName').value;
    this.company.companyTurnOver = this.form.get('companyTurnover').value;
    this.company.companyWebsite = this.form.get('companyWebsite').value;
    this.company.stockExchanges = [new StockExchange(this.form.get('stockPrice').value,
    this.form.get('companyCode').value)];

    //let baseUrl = "http://localhost:8037/api/v1.0/market/company/register";
    let baseUrl = "https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/company-service";
    
    let username = 'actuator';
    let password = 'actuator';
    let headers = new HttpHeaders();
    
    headers.set('Access-Control-Allow-Origin', '*');
    headers.set('Content-Type', 'application/json')
    headers.set('Authorization', 'Basic '+ btoa(username + ':' + password));
    
    console.log(btoa(username + ':' + password));
    console.log("Request is :" +  this.company);
    
    return this.http.post<Company>(baseUrl, this.company, { 'headers': headers });
  }
}
