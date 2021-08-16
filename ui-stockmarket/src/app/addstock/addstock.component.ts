import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { ErrorMessage } from '../model/ErrorMessage';
import { StockExchange } from '../model/StockExchange';

@Component({
  selector: 'app-addstock',
  templateUrl: './addstock.component.html',
  styleUrls: ['./addstock.component.css']
})
export class AddstockComponent implements OnInit {

  form: FormGroup;
  stockExchange = new StockExchange();
  stockResponse: StockExchange;
  stockDetailsFlag: boolean = false;
  errorMsg: ErrorMessage;
  errorFlag: boolean = false;

  constructor(public fb: FormBuilder, public http: HttpClient) {
    this.form = this.fb.group({
      companyCode: [''],
      stockPrice: ['']
    })
  }

  ngOnInit(): void {
  }

  submitForm() {
    console.log(this.form.value);

    this.addStockDetails().subscribe(
      (response) => {
        console.log(response);
        this.stockResponse = response;
        this.stockDetailsFlag = true;
        this.errorFlag = false;
      },
      (error) => {
        this.errorMsg = error.error;
        this.stockDetailsFlag = false;
        this.errorFlag = true;
      }
    )

  }

  addStockDetails() :Observable<StockExchange> {
    let companyCode = this.form.get('companyCode').value;
    //let baseUrl = "http://localhost:8038/api/v1.0/market/stock/add/";
    let baseUrl = "https://y032zgh7qg.execute-api.us-east-2.amazonaws.com/prod/";
    this.stockExchange.price = this.form.get('stockPrice').value;
    this.stockExchange.companyCode = this.form.get('companyCode').value;
    console.log(this.stockExchange);

    let username = 'actuator';
    let password = 'actuator';
    let headers = new HttpHeaders()
    headers.set('Access-Control-Allow-Origin', '*');
    headers.set('Content-Type', 'application/json');
    headers.set('Authorization', 'Basic '+ btoa(username + ':' + password));
    
     return this.http.post<StockExchange>(baseUrl + companyCode, this.stockExchange,
      { 'headers': headers });
  }
}
