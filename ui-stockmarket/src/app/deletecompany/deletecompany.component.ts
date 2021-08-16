import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ErrorMessage } from '../model/ErrorMessage';

@Component({
  selector: 'app-deletecompany',
  templateUrl: './deletecompany.component.html',
  styleUrls: ['./deletecompany.component.css']
})
export class DeletecompanyComponent implements OnInit {

  companyCode: String;
  errorMsg: ErrorMessage;
  errorFlag: boolean = false;
  
  constructor(public http: HttpClient) { }

  ngOnInit(): void {
  }

  deleteCompany() {
    console.log('Company Code is : ' + this.companyCode);

    this.removeCompany().subscribe(
      (response) => {
        console.log(response);
        this.errorFlag = false;
        alert("Company Code " + this.companyCode + " removed successfully");
      },
      (error) => {
        this.errorMsg = error.error;
        this.errorFlag = true;
      }
    )
    
  }

  removeCompany() {
    let username = 'actuator';
    let password = 'actuator';
    //let baseUrl = "http://localhost:8037/api/v1.0/market/company/delete/" + this.companyCode;
    let baseUrl = "https://wwvmzopnwl.execute-api.us-east-2.amazonaws.com/prod/" + this.companyCode;

    let headers = new HttpHeaders();
    headers.set('Allow-Control-Access-origin', '*');
    headers.set('Content-Type', 'application/json');
    headers.set('Authorization', 'Basic '+ btoa(username + ':' + password));
    return this.http.delete(baseUrl, {'headers': headers});
  }

}
