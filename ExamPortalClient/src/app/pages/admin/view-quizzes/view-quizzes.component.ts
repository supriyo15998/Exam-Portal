import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Quiz } from 'src/app/entities/quiz';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {
  quizzes: Quiz[] = []
  isLoading = true
  constructor(private quizService: QuizService, private snackBar: MatSnackBar) { }

  ngOnInit (): void {
    this.quizzes = []
    this.quizService.getAllQuiz().subscribe(
      data => {
        this.isLoading = false;
        this.quizzes = data;
      },
      error => {
        this.isLoading = false;
        if (error.status != 404) {
          this.snackBar.open('Something went wrong !', '', {
            duration: 2000,
            horizontalPosition: 'left',
            verticalPosition: 'bottom',
            panelClass: ['error-snackbar']
          })
        }

      }
    )
  }
  deteleQuiz (qid: number) {
    if (confirm(`Are you sure want to delete quiz id: ${qid}`)) {
      this.isLoading = true
      this.quizService.removeQuiz(qid).subscribe(
        data => {
          this.isLoading = false
          this.snackBar.open(data.message, '', {
            duration: 2000,
            horizontalPosition: 'left',
            verticalPosition: 'bottom',
            panelClass: ['success-snackbar']
          })
          this.quizzes = this.quizzes.filter((q) => q.qid != qid);
        },
        error => {
          this.isLoading = false
          this.snackBar.open('Something went wrong !', '', {
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
