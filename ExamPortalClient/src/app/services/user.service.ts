import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../entities/user';
import { Helper } from '../helpers/helper';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  addUser (user: User): Observable<any> {
    return this.http.post(Helper.baseUrl + `user/create`, user);
  }

}
