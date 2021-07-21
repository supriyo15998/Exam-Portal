import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Helper } from '../helpers/helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  getCurrentUser () {
    return this.http.get(Helper.baseUrl + `current-user`);
  }

  generateToken (loginData: any): Observable<any> {
    return this.http.post(Helper.baseUrl + `generate-token`, loginData);
  }

  setToken (token) {
    localStorage.setItem("token", token);

    return true;
  }

  isLoggedIn () {
    let token = localStorage.getItem("token");
    if (token == undefined || token == '' || token == null)
      return false;
    return true;
  }

  logout () {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }

  getToken () {
    return localStorage.getItem("token");
  }

  setUser (user) {
    localStorage.setItem("user", JSON.stringify(user));
  }

  getUser () {
    let user = localStorage.getItem("user");
    if (user != null && user != undefined && user != '') {
      return JSON.parse(user);
    }
    this.logout();
    return null;
  }

  getUserRole () {
    let user = this.getUser()
    return user.authorities[0].authority;
  }

}
