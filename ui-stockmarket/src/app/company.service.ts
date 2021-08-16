import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Company } from './model/Company';
import { CompanyStockDetails } from './model/CompanyStockDetails';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private companyDetailsSource = new Subject<CompanyStockDetails>();
  companyDetails$ = this.companyDetailsSource.asObservable();

  constructor() { }

  sendCompanyDetails(company: CompanyStockDetails) {
    this.companyDetailsSource.next(company);
  }
}
