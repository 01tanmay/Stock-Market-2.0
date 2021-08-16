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

    this.authService.login(this.username, this.password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.reloadPage();
        console.log('before routing');
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
      
    );
    this.router.navigate(['welcome']);
    sessionStorage.setItem('user', this.username);
  }

   reloadPage(): void {
    console.log('inside reload');
    window.location.reload();
  }
}
