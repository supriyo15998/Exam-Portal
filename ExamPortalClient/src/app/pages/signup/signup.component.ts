import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from 'src/app/entities/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService, private snackbar: MatSnackBar) { }

  user: User = new User();
  @ViewChild("addUserForm")
  private form: NgForm;
  ngOnInit (): void {
  }
  clearForm () {
    this.form.reset();
  }
  registerFormSubmit () {

    this.userService.addUser(this.user).subscribe(
      data => {
        this.snackbar.open(`${data.username} registered successfully !`, '', {
          duration: 2000,
          horizontalPosition: 'left',
          verticalPosition: 'bottom',
          panelClass: ['success-snackbar']
        });
        this.form.reset();
      },
      error => {
        let errorMsg = "";
        error.error.messages.forEach(element => {
          errorMsg += (element + ", ")
        });
        this.snackbar.open(errorMsg, '', {
          duration: 2000,
          horizontalPosition: 'left',
          'verticalPosition': 'bottom',
          panelClass: ['error-snackbar']
        })

      }
    )
  }

}
