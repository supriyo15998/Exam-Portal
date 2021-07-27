import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/entities/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent implements OnInit {
  category: Category = new Category();
  categoryId: number;
  isLoading: boolean;
  @ViewChild("editCategoryForm")
  private form: NgForm;
  constructor(private categoryService: CategoryService, private route: ActivatedRoute, private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit (): void {
    this.isLoading = true
    this.categoryId = this.route.snapshot.params.cid;
    this.categoryService.getCategory(this.categoryId).subscribe(
      data => {
        this.isLoading = false
        this.category = data;
      },
      error => {
        this.isLoading = false
        this.snackBar.open(error.error.messages[0], '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['error-snackbar']
        })
      }
    )

  }

  editCategory () {
    this.isLoading = true
    this.categoryService.updateCategory(this.category.cid, this.category).subscribe(
      data => {
        this.isLoading = false
        this.snackBar.open(data.message, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['success-snackbar']
        })

        this.router.navigate(['/admin/categories'])
      },
      error => {
        this.isLoading = false
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
