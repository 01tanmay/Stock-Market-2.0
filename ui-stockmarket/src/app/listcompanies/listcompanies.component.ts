import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../model/Company';
import { ErrorMessage } from '../model/ErrorMessage';

@Component({
  selector: 'app-listcompanies',
  templateUrl: './listcompanies.component.html',
  styleUrls: ['./listcompanies.component.css']
})
export class ListcompaniesComponent implements OnInit {

  companyInfos : Company[];
  errorMsg: ErrorMessage;
  errorFlag: boolean = false;
  
  constructor(public http: HttpClient) { }

  ngOnInit(): void {

    this.getAllCompanies().subscribe(
      (response) =>  {
        console.log(response);
        this.companyInfos = response;
        this.errorFlag = false;
      },
      (error) => {
        this.errorMsg = error.error;
        this.errorFlag = true;
      }
    )
  }

  getAllCompanies() : Observable<Company[]> {
 
    //let baseUrl="http://localhost:8037/api/v1.0/market/company/getall";
    let baseUrl="https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/company-service";
    
    let headers = new HttpHeaders();
    headers.set('Access-Control-Allow-Origin', '*');
    headers.set('Content-Type', 'application/json');
    
    return this.http.get<Company[]>(baseUrl, {'headers': headers});
  }

}
