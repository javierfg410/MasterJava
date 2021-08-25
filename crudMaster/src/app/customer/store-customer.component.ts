import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, NgForm, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../models/customer';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-store-customer',
  templateUrl: './store-customer.component.html',
  styleUrls: ['./store-customer.component.css']
})
export class StoreCustomerComponent implements OnInit {

  dni: string = '';
  name: string = '';
  lastname: string = '';
  address: string = '';
  country: string = '';
  email: string = '';
  phone: number = 0;



  constructor(
    private customerService: CustomerService,
    private toastr: ToastrService,
    private router: Router

  ) { }


  ngOnInit(): void {

  }
  onCreate(regForm: NgForm): void {
    if (regForm.valid) {
      const customer = new Customer(this.dni,this.name,this.lastname,this.address,this.country,this.email,this.phone);
      this.customerService.store(customer).subscribe(
        data => {
          this.toastr.success('Cliente creado', 'OK', {
            timeOut: 3000
          });
          this.router.navigate(['/customer/list']);
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
          case 'dni': text = 'DNI obligatorio y valido'; break;
          case 'name': text = 'Nombre obligatorio'; break;
          case 'lastname': text = 'Apellidos obligatorios'; break;
          case 'email': text = 'Email obligatorios y valido'; break;
          case 'phone': text = 'Telefono obligatorios, debe ser un numero mayor a 8'; break;
          case 'address': text = 'Dirección obligatoria'; break;
          case 'country': text = 'País obligatorio'; break;
  
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
    const control = controls[key];
    if (control instanceof FormGroup) {
      errors = errors.concat(getFormValidationErrors(control.controls));
    }
    const controlErrors: ValidationErrors = controls[key].errors;
    if (controlErrors !== null) {
      Object.keys(controlErrors).forEach(keyError => {
        errors.push({
          control_name: key,
          error_name: keyError,
          error_value: controlErrors[keyError]
        });
      });
    }
  });
  return errors;
}