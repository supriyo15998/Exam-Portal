import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quiz } from '../entities/quiz';
import { Helper } from '../helpers/helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  prefix: string = "quiz/"
  constructor(private http: HttpClient) { }

  getAllQuiz (): Observable<any> {
    return this.http.get(Helper.baseUrl + this.prefix + `get/all`);
  }

  createQuiz (quiz: Quiz): Observable<any> {
    return this.http.post(Helper.baseUrl + this.prefix + `create`, quiz);
  }

  removeQuiz (qid: number): Observable<any> {
    return this.http.delete(Helper.baseUrl + this.prefix + `delete/${qid}`);
  }

}
