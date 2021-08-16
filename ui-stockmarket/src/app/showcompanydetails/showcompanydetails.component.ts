import { Component, Input, OnInit } from '@angular/core';
import { CompanyService } from '../company.service';
import { Company } from '../model/Company';
import { CompanyStockDetails } from '../model/CompanyStockDetails';
import { StockExchange } from '../model/StockExchange';

@Component({
  selector: 'app-showcompanydetails',
  templateUrl: './showcompanydetails.component.html',
  styleUrls: ['./showcompanydetails.component.css']
})
export class ShowcompanydetailsComponent implements OnInit {

  @Input() compResponse: any;
  
  constructor(public compService: CompanyService) { }

  ngOnInit(): void {  }

}
