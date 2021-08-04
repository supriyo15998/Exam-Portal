import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from 'src/app/entities/question';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrls: ['./update-question.component.css']
})
export class UpdateQuestionComponent implements OnInit {
  question: Question = new Question();
  questionId: number;
  isLoading: boolean;
  constructor(private route: ActivatedRoute, private questionService: QuestionService, private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit (): void {
    this.isLoading = true
    this.questionId = this.route.snapshot.params.questionId;
    this.questionService.getQuestion(this.questionId).subscribe(
      data => {
        this.isLoading = false;
        this.question = data;
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

  updateQuestion () {
    this.question.quizId = this.question.quiz.qid;
    this.isLoading = true
    this.questionService.updateQuestion(this.question.questionId, this.question).subscribe(
      data => {
        this.isLoading = false;
        this.snackBar.open(data.message, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['success-snackbar']
        })

        this.router.navigate([`/admin/view-questions/${this.question.quiz.qid}/${this.question.quiz.title}`])
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
