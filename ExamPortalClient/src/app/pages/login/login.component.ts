import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    username: '',
    password: ''
  }
  constructor(private snackBar: MatSnackBar, private loginService: LoginService, private router: Router) { }

  ngOnInit (): void {
  }
  loginFormSubmit () {
    this.loginService.generateToken(this.loginData).subscribe(
      data => {

        this.loginService.setToken(data.token);
        this.loginService.getCurrentUser().subscribe(
          (user: any) => {
            console.log(user);
            this.loginService.setUser(user);
            this.snackBar.open(`Welcome ${user.username}`, '', {
              duration: 2000,
              horizontalPosition: 'left',
              verticalPosition: 'bottom',
              panelClass: ['success-snackbar']
            })
            //redirect based on role
            if (this.loginService.getUserRole() == "ADMIN") {
              this.router.navigate(['admin'])
              this.loginService.loginStatusSubject.next(true);
              // window.location.href = "/admin"
            } else if (this.loginService.getUserRole() == "NORMAL") {
              this.router.navigate(['user-dashboard'])
              this.loginService.loginStatusSubject.next(true);
              // window.location.href = "/user-dashboard"
            } else {
              this.loginService.logout();
              // location.reload()
            }
          },
          error => {
            console.log(error);

          }
        )
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
}
