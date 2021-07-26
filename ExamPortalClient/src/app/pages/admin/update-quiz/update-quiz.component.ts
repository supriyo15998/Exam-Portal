import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/entities/category';
import { Quiz } from 'src/app/entities/quiz';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute, private quizService: QuizService, private snackBar: MatSnackBar, private categoryService: CategoryService) { }

  qId = undefined
  isLoading;
  categories: Category[] = []
  quiz: Quiz = new Quiz();
  ngOnInit (): void {
    this.isLoading = true;
    this.qId = this.route.snapshot.params.qid;
    this.quizService.getQuiz(this.qId).subscribe(
      data => {
        this.isLoading = false
        this.quiz = data;
        console.log(this.quiz);
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
    this.categoryService.getAllCategories().subscribe(
      data => {
        this.isLoading = false
        this.categories = data;
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

  updateQuiz () {
    this.isLoading = true;
    this.quizService.alterQuiz(this.quiz, this.quiz.qid).subscribe(
      data => {
        this.isLoading = false
        this.snackBar.open(data.message, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['success-snackbar']
        })
        this.router.navigate(['/admin/view-quizzes']);
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
