import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';
import { TokenStorageService } from './service/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate{

  constructor(private authService: AuthenticationService, private token: TokenStorageService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if(this.authService.isUserLoggedIn()) {
      return true;
    }
    this.router.navigate(['login']);
    return false;
    
  }
}
