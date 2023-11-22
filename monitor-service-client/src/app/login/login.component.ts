import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  errorMessage: string = '';
  password: string = '';

  constructor(private authService: AuthService,private router: Router) {}

  onSubmit() {
    this.authService.login(this.username, this.password)
      .subscribe(
        (response: any) => {
          console.log('Login successful:', response);
          this.router.navigate(['/table'])
        },
        (error) => {
          this.errorMessage = 'wrong username/password, try again ';

          console.error('Login failed:', error);
        }
      );
  }

  
}