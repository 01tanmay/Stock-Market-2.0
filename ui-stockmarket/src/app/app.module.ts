import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { FooterComponent } from './footer/footer.component';
import { MenuComponent } from './menu/menu.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { AddcompanyComponent } from './addcompany/addcompany.component';
import { SearchcompanyComponent } from './searchcompany/searchcompany.component';
import { DeletecompanyComponent } from './deletecompany/deletecompany.component';
import { AddstockComponent } from './addstock/addstock.component';
import { GetcompanyComponent } from './getcompany/getcompany.component';
import { ListcompaniesComponent } from './listcompanies/listcompanies.component';
import { ShowcompanydetailsComponent } from './showcompanydetails/showcompanydetails.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    FooterComponent,
    MenuComponent,
    WelcomeComponent,
    AddcompanyComponent,
    SearchcompanyComponent,
    DeletecompanyComponent,
    AddstockComponent,
    GetcompanyComponent,
    ListcompaniesComponent,
    ShowcompanydetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
