import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../model/Company';
import {CompanyService} from '../company.service';
import { Router } from '@angular/router';
import { ErrorMessage } from '../model/ErrorMessage';

@Component({
  selector: 'app-getcompany',
  templateUrl: './getcompany.component.html',
  styleUrls: ['./getcompany.component.css']
})
export class GetcompanyComponent implements OnInit {

  companyCode: String;
  companyResponse: Company;
  getCompanyDetailsFlag: boolean = false;
  errorMsg: ErrorMessage;
  errorFlag: boolean = false;

  constructor(public http: HttpClient, public companyService: CompanyService,
    public router: Router) { }

  ngOnInit(): void {
  }

  retrieveCompany() {
    console.log('Company Code is : ' + this.companyCode);
    this.getCompanyDetails().subscribe(
      (response) => {
        console.log(response);
        this.companyResponse = response;
        this.getCompanyDetailsFlag = true;
        this.errorFlag = false;
      },
      (error) => {
        this.errorMsg = error.error;
        this.getCompanyDetailsFlag = false;
        this.errorFlag = true;
      }
    );

  }

  getCompanyDetails() : Observable<Company> {
    let username = 'actuator';
    let password = 'actuator';
    //let baseUrl = "http://:8083/api/v1.0/market/company/info/" + this.companyCode;
    //let baseUrl = "http://13.59.147.29:8083/api/v1.0/market/company/info/" + this.companyCode;
    let baseUrl = "https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/" + this.companyCode;
    let headers = new HttpHeaders();
    headers.set('Allow-Control-Access-origin', '*');
    headers.set('Content-Type', 'application/json');
    headers.set('Authorization', 'Basic '+ btoa(username + ':' + password));
    console.log(btoa(username + ':' + password));
    return this.http.get<Company>(baseUrl, {'headers': headers});
  }
}
