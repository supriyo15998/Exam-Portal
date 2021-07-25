import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Category } from 'src/app/entities/category';
import { Quiz } from 'src/app/entities/quiz';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {
  quiz: Quiz = new Quiz();
  categories: Category[] = []
  isLoading = true;
  @ViewChild("addQuizForm")
  private form: NgForm;
  constructor(private categoryService: CategoryService, private snackBar: MatSnackBar, private quizService: QuizService) { }

  ngOnInit (): void {
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

  addQuiz () {
    this.isLoading = true
    this.quizService.createQuiz(this.quiz).subscribe(
      data => {
        this.isLoading = false
        this.snackBar.open(data.message, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['success-snackbar']
        })
        this.form.reset()
      },
      error => {
        this.isLoading = false
        let errorMsg = "";
        error.error.messages.forEach(element => {
          errorMsg += (element + ", ")
        });
        this.snackBar.open(errorMsg, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['error-snackbar']
        })
      }
    )
  }

}
