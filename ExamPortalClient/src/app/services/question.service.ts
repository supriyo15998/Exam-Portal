import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Question } from '../entities/question';
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

  createQuestion (question: Question): Observable<any> {
    return this.http.post(Helper.baseUrl + `${this.prefix}create`, question);
  }

  deleteQuestion (questionId: number): Observable<any> {
    return this.http.delete(Helper.baseUrl + `${this.prefix}delete/${questionId}`);
  }

  getQuestion (questionId: number): Observable<any> {
    return this.http.get(Helper.baseUrl + `${this.prefix}get/question/${questionId}`);
  }

  updateQuestion (questionId: number, question: Question): Observable<any> {
    return this.http.put(Helper.baseUrl + `${this.prefix}update/${questionId}`, question);
  }

}
