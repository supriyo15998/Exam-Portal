import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../entities/category';
import { Helper } from '../helpers/helper';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  prefix = "category/"
  constructor(private http: HttpClient) { }

  getAllCategories (): Observable<any> {
    return this.http.get(Helper.baseUrl + this.prefix + "get/all");
  }

  getCategory (categoryId: number): Observable<any> {
    return this.http.get(Helper.baseUrl + `${this.prefix}get/${categoryId}`);
  }

  addCategory (category: Category): Observable<any> {
    return this.http.post(Helper.baseUrl + this.prefix + "create", category);
  }

  updateCategory (categoryId: number, category: Category): Observable<any> {
    return this.http.put(Helper.baseUrl + `${this.prefix}update/${categoryId}`, category);
  }

  removeCategory (categoryId: number): Observable<any> {
    return this.http.delete(Helper.baseUrl + `${this.prefix}delete/${categoryId}`);
  }

}
