import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  authenticateUser(username: string, password: string) {
    if(username === "tanmay" && password === "tanmay") {
      sessionStorage.setItem('user', username);
      return true;
    }
    return false;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('user');
    if(user === null) {
      return false;
    }
    return true;
  }

  logoutUser() {
    sessionStorage.removeItem('user');
  }
}
