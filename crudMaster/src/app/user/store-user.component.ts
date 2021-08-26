import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, NgForm, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginUser } from '../models/login-user';
import { Role } from '../models/role';
import { User } from '../models/user';
import { AuthService } from '../service/auth.service';
import { RoleService } from '../service/role.service';
import { TokenService } from '../service/token.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-store-user',
  templateUrl: './store-user.component.html',
  styleUrls: ['./store-user.component.css']
})
export class StoreUserComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUser: LoginUser;
  name: string = '';
  lastname: string = '';
  phone: number;
  username: string;
  password: string;
  role: Role;
  roles: string[] = [];
  errMsj: string;

  constructor(
    private userService: UserService,
    private rolesService: RoleService,
    private tokenSevice: TokenService,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    if (this.tokenSevice.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenSevice.getAuthorities();
    }
  }

  onCreate(regForm: NgForm): void {
    if (regForm.valid) {
      this.username = this.username.toLowerCase();
      const user = new User(this.role, this.name, this.lastname, this.username, this.password, this.phone);
     
      this.userService.store(user).subscribe(
        data => {
          this.toastr.success('Usuario creada', 'OK', {
            timeOut: 3000
          });
          this.router.navigate(['/user/list']);
        },
        err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000
          });
          this.router.navigate(['/']);
        }
      );
    }else{
      const error: AllValidationErrors = getFormValidationErrors(regForm.controls).shift();
      console.log(error);
      if (error) {
        let text;
        switch (error.control_name) {
          case 'name': text = 'Nombre obligatorio'; break;
          case 'lastname': text = 'Apellidos obligatorios'; break;
          case 'username': text = 'Nombre de usuario obligatorios'; break;
          case 'password': text = 'Contraseña debe de ser mayor a 6 y menor de 12. Sin caracteres especiales'; break;
          case 'phone': text = 'Telefono obligatorios, debe ser un numero mayor a 8'; break;
  
          default: text = `${error.control_name}: ${error.error_name}: ${error.error_value}`;
        }
        this.toastr.error(text, 'Error de validación', {
          timeOut: 5000
        });
      }
    }
  }

}
export interface AllValidationErrors {
  control_name: string;
  error_name: string;
  error_value: any;
}
export interface FormGroupControls {
  [key: string]: AbstractControl;
}

export function getFormValidationErrors(controls: FormGroupControls): AllValidationErrors[] {
  let errors: AllValidationErrors[] = [];
  Object.keys(controls).forEach(key => {
    const control = controls[ key ];
    if (control instanceof FormGroup) {
      errors = errors.concat(getFormValidationErrors(control.controls));
    }
    const controlErrors: ValidationErrors = controls[ key ].errors;
    if (controlErrors !== null) {
      Object.keys(controlErrors).forEach(keyError => {
        errors.push({
          control_name: key,
          error_name: keyError,
          error_value: controlErrors[ keyError ]
        });
      });
    }
  });
  return errors;
}

