import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Category } from 'src/app/entities/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {
  category: Category = new Category();
  @ViewChild("addCategoryForm")
  private form: NgForm;
  constructor(private categoryService: CategoryService, private snackBar: MatSnackBar) { }

  ngOnInit (): void {
  }

  addCategory () {
    this.categoryService.addCategory(this.category).subscribe(
      data => {
        this.snackBar.open(data.message, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['success-snackbar']
        })
        this.form.reset()
      },
      error => {
        this.snackBar.open('Something Went Wrong !', '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['error-snackbar']
        })
      }
    )
  }

}
