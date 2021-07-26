import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Helper } from '../helpers/helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  prefix: string = "question/"

  constructor(private http: HttpClient) { }

  getQuestionsOfQuizAdmin (quizId: number): Observable<any> {
    return this.http.get(Helper.baseUrl + `${this.prefix}get/quiz/all/${quizId}`);
  }

}
