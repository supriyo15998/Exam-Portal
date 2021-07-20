import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';
// import { User } from 'src/app/entities/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // user: User = new User();
  loginData = {
    username: '',
    password: ''
  }
  constructor(private snackBar: MatSnackBar, private loginService: LoginService) { }

  ngOnInit (): void {
  }
  loginFormSubmit () {
    console.log(this.loginData);
    this.loginService.generateToken(this.loginData).subscribe(
      data => {
        console.log(data);

        this.loginService.setToken(data.token);
        this.loginService.getCurrentUser().subscribe(
          (user: any) => {
            console.log(user);
            this.loginService.setUser(user);
            this.snackBar.open(`Welcome ${user.username}`, '', {
              duration: 2000,
              horizontalPosition: 'left',
              'verticalPosition': 'bottom',
              panelClass: ['success-snackbar']
            })
            //redirect based on role
          },
          error => {
            console.log(error);

          }
        )
      },
      error => {
        console.log(error);
        this.snackBar.open(error.error.messages[0], '', {
          duration: 2000,
          horizontalPosition: 'left',
          'verticalPosition': 'bottom',
          panelClass: ['error-snackbar']
        })
      }
    )
  }
}
