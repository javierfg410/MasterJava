import { Component, OnInit } from '@angular/core';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { LoginUser } from '../models/login-user';
import { AuthService } from '../service/auth.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUser: LoginUser;
  username: string;
  password: string;
  roles: string[] = [];
  errMsj: string;
  constructor(
    private tokenSevice: TokenService,
    private authService: AuthService,
    private router: Router,

  ) {

  }

  ngOnInit(): void {
    if (this.tokenSevice.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenSevice.getAuthorities();
    }
  }

  onLogin(): void {
    this.loginUser = new LoginUser(this.username, this.password);
    this.authService.login(this.loginUser).subscribe(
      data => {
        this.isLogged = true;
        this.isLoginFail = false;

        this.tokenSevice.setToken(data.token);
        this.tokenSevice.setUsername(data.username);
        this.tokenSevice.setAuthorities(data.authorities);
        this.roles = data.authorities;
        this.router.navigate(['/']);
      },
      err => {
        this.isLogged = false;
        this.isLoginFail = true;
        this.errMsj = err.error.mensaje;
        console.log(err.error);
      }
    );
}
}
