import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CompanyService } from '../company.service';
import { Company } from '../model/Company';
import { CompanyStockDetails } from '../model/CompanyStockDetails';
import { ErrorMessage } from '../model/ErrorMessage';

@Component({
  selector: 'app-searchcompany',
  templateUrl: './searchcompany.component.html',
  styleUrls: ['./searchcompany.component.css']
})
export class SearchcompanyComponent implements OnInit {

  form: FormGroup;
  compResponse: CompanyStockDetails;
  searchCompanyFlag: boolean = false;
  errorMsg: ErrorMessage;
  errorFlag: boolean = false;
  
  constructor(public fb: FormBuilder, public http: HttpClient, 
    public compService: CompanyService, public router: Router) {
    this.form = this.fb.group({
      companyCode: [''],
      fromDate: [''],
      toDate: ['']
    })
   }

  ngOnInit(): void {
  }

  submitForm() {
    console.log(this.form.value)

    this.searchCompany().subscribe(
      (response) => {
        console.log(response);
        this.compResponse = response;
        this.compResponse.fromDate = this.form.get('fromDate').value;
        this.compResponse.toDate = this.form.get('toDate').value;
        this.searchCompanyFlag = true;
        this.errorFlag = false;
      },
      (error) => {
        this.errorMsg = error.error;
        console.log(this.errorMsg.errors);
        console.log(this.errorMsg.status);
        console.log(this.errorMsg.message);
        this.searchCompanyFlag = false;
        this.errorFlag = true;
      }
    )
  }

  searchCompany(): Observable<CompanyStockDetails> {
    let companyCode =this.form.get('companyCode').value;
    let startDate = this.form.get('fromDate').value;
    let endDate = this.form.get('toDate').value;

    let baseUrl= "http://13.59.147.29:8082/api/v1.0/market/stock/get/"+companyCode+"?startDate="+startDate+"&endDate="+endDate;
    //let baseUrl= "https://y5xdu61xe4.execute-api.us-east-2.amazonaws.com/prod/"+companyCode+"?startDate="+startDate+"&endDate="+endDate;
    //let baseUrl= "http://localhost:8082/api/v1.0/market/stock/get/"+companyCode+"?startDate="+startDate+"&endDate="+endDate;
    
    //https://y5xdu61xe4.execute-api.us-east-2.amazonaws.com/prod/
    console.log('baseurl: ' + baseUrl);
    let username = 'actuator';
    let password = 'actuator';
    let headers = new HttpHeaders();
    
    headers.set('Access-Control-Allow-Origin', '*');
    headers.set('Content-Type', 'application/json');
    headers.set('Authorization', 'Basic '+ btoa(username + ':' + password));
    
    console.log(btoa(username + ':' + password));

    return this.http.get<CompanyStockDetails>(baseUrl, {'headers': headers})
  }
}
