import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from 'src/app/entities/question';
import { Quiz } from 'src/app/entities/quiz';
import { QuestionService } from 'src/app/services/question.service';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
  @ViewChild("addQuestionForm")
  private form: NgForm;
  quizId;
  quiz: Quiz = new Quiz();
  question: Question = new Question();
  isLoading;
  constructor(private router: Router, private route: ActivatedRoute, private quizService: QuizService, private snackBar: MatSnackBar, private questionService: QuestionService) { }

  ngOnInit (): void {
    this.isLoading = true;
    this.quizId = this.route.snapshot.params.qid;
    this.quizService.getQuiz(this.quizId).subscribe(
      data => {
        this.isLoading = false;
        this.quiz = data;
        this.question.quizId = data.qid;
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

  addQuestion () {
    this.isLoading = true;
    this.questionService.createQuestion(this.question).subscribe(
      data => {
        this.isLoading = false;
        this.snackBar.open(data.message, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['success-snackbar']
        })
        this.form.reset()
        this.router.navigate([`/admin/view-questions/${this.quiz.qid}/${this.quiz.title}`])
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
