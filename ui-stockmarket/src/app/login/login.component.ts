import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { AuthenticationService } from '../service/authentication.service';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  invalidLogin = false;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  constructor(private router: Router,
    private authenticateService: AuthenticationService, private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
  }

  handleLogin() {
    console.log("Entering Validation")
    console.log(this.username + " " + this.password)

    /** 
    if (this.authenticateService.authenticateUser(this.username, this.password)) {
      console.log("valid user")
      this.invalidLogin = false;
      this.router.navigate(['welcome']);
    } else {
      this.invalidLogin = true;
    }**/
    this.authService.login(this.username, this.password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        //this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
        console.log('before routing');
       //this.routerservice.routeToHome();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
      
    );
    this.router.navigate(['welcome']);
  }

   reloadPage(): void {
    console.log('inside reload');
    window.location.reload();
    
  }
}
