import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddcompanyComponent } from './addcompany/addcompany.component';
import { AddstockComponent } from './addstock/addstock.component';
import { DeletecompanyComponent } from './deletecompany/deletecompany.component';
import { GetcompanyComponent } from './getcompany/getcompany.component';
import { ListcompaniesComponent } from './listcompanies/listcompanies.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RouteGuardService } from './route-guard.service';
import { SearchcompanyComponent } from './searchcompany/searchcompany.component';
import { ShowcompanydetailsComponent } from './showcompanydetails/showcompanydetails.component';
import { WelcomeComponent } from './welcome/welcome.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService] },
  { path: 'welcome', component: WelcomeComponent, canActivate: [RouteGuardService] },
  { path: 'addcompany', component: AddcompanyComponent, canActivate: [RouteGuardService] },
  { path: 'searchcompany', component: SearchcompanyComponent, canActivate: [RouteGuardService] },
  { path: 'deletecompany', component: DeletecompanyComponent, canActivate: [RouteGuardService] },
  { path: 'addstock', component: AddstockComponent, canActivate: [RouteGuardService] },
  { path: 'getcompany', component: GetcompanyComponent, canActivate: [RouteGuardService] },
  { path: 'listcompanies', component: ListcompaniesComponent, canActivate: [RouteGuardService]},
  { path: 'showcompanydetails', component: ShowcompanydetailsComponent, canActivate: [RouteGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
