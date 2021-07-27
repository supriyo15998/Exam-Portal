import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Question } from 'src/app/entities/question';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-view-questions',
  templateUrl: './view-questions.component.html',
  styleUrls: ['./view-questions.component.css']
})
export class ViewQuestionsComponent implements OnInit {
  qId;
  questions: Question[] = [];
  isLoading;
  constructor(private route: ActivatedRoute, private questionService: QuestionService, private snackBar: MatSnackBar) { }

  ngOnInit (): void {
    this.questions = []
    this.isLoading = true;
    this.qId = this.route.snapshot.params.qid;
    this.questionService.getQuestionsOfQuizAdmin(this.qId).subscribe(
      data => {
        this.isLoading = false;
        this.questions = data;
      },
      error => {
        this.isLoading = false;
        this.snackBar.open(error.error.messages[0], '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['error-snackbar']
        })
      }
    )
  }

  dropQuestion (questionId: number) {
    if (confirm(`Are you sure want to delete question with id: ${questionId}`)) {
      this.isLoading = true;
      this.questionService.deleteQuestion(questionId).subscribe(
        data => {
          this.isLoading = false;
          this.ngOnInit();
        },
        error => {
          this.isLoading = false;
          this.snackBar.open(error.error.messages[0], '', {
            duration: 2000,
            horizontalPosition: 'left',
            verticalPosition: 'bottom',
            panelClass: ['error-snackbar']
          })
        }
      )
    }

  }
}
