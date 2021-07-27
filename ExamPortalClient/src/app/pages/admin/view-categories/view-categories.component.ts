import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Category } from 'src/app/entities/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {
  categories: Category[] = []
  isLoading = true
  constructor(private categoryService: CategoryService, private snackBar: MatSnackBar) { }

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

  deleteCategory (categoryId: number) {
    if (confirm(`Are you sure want to delete category with id: ${categoryId}`)) {
      this.isLoading = true
      this.categoryService.removeCategory(categoryId).subscribe(
        data => {
          this.isLoading = false;
          this.snackBar.open(data.message, '', {
            duration: 2000,
            horizontalPosition: 'left',
            verticalPosition: 'bottom',
            panelClass: ['success-snackbar']
          })
          this.ngOnInit()
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
