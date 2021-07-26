import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Question } from 'src/app/entities/question';
import { Quiz } from 'src/app/entities/quiz';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
  quizId;
  quiz: Quiz = new Quiz();
  question: Question = new Question();
  constructor(private route: ActivatedRoute, private quizService: QuizService, private snackBar: MatSnackBar) { }

  ngOnInit (): void {
    this.quizId = this.route.snapshot.params.qid;
    this.quizService.getQuiz(this.quizId).subscribe(
      data => {
        this.quiz = data;
        this.question.quizId = data.qid;
      },
      error => {
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

  }

}
