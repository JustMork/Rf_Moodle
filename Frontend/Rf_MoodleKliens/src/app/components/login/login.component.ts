import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService, LoginRequest } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private formBuilder: FormBuilder,

  ) {
    this.userService.authenticatedUser$.subscribe({
      next: (user: User | null) => {
        if (user !== null) {
          this.router.navigate(['/']);
        }
      },
    })
  }
  loginForm = this.formBuilder.nonNullable.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  }, { updateOn: 'blur' });

  get username() { return this.loginForm.get('username') }
  get password() { return this.loginForm.get('password') }

  login() {
    const loginData: LoginRequest = {
      username: this.username!.value,
      password: this.password!.value
    }

    this.authService.login(loginData).subscribe({
      next: () => this.router.navigate(['/']),
    });
  }
}