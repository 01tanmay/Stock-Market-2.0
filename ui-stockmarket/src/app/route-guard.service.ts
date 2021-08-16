import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';
import { TokenStorageService } from './service/token-storage.service';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate{

  currentUser: any;

  constructor(private authService: AuthenticationService, private token: TokenStorageService, private router: Router) { }

  
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if(this.authService.isUserLoggedIn()) {
      return true;
    }
    this.router.navigate(['login']);
    return false;
    
  }
  /**
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> 
  | Promise<boolean> | boolean {

    this.currentUser = this.token.getUser();
    console.log('currenttoken: '+this.token.getToken());
    console.log('currentuser: ' + this.currentUser.username);

    const bearerToken = this.token.getToken();
    const sessionUsername = this.token.getToken();
  
    if (bearerToken != null && sessionUsername != null) {
    return true;
  } else {
    this.router.navigate(['login']);
    return false;
  }
  }
  */

}
