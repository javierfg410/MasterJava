import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, NgForm, ValidationErrors } from '@angular/forms';
import { RouteConfigLoadEnd, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Pet } from '../models/pet';
import { PetType } from '../models/petType';
import { PetTypeService } from '../service/pet-type.service';
import { PetService } from '../service/pet.service';

@Component({
  selector: 'app-store-pet',
  templateUrl: './store-pet.component.html',
  styleUrls: ['./store-pet.component.css']
})
export class StorePetComponent implements OnInit {

  petTypes: PetType[] = [];

  name: string = '';
  cost: number = 0;
  petTypeId: number = null;
  petType: PetType = PetType;

  constructor(
    private petService: PetService,
    private petTypeService: PetTypeService,
    private toastr: ToastrService,
    private router: Router

  ) { }


  ngOnInit(): void {
    this.chargePetTypes();
  }
  chargePetTypes(): void {
    this.petTypeService.list().subscribe(
      data => {
        this.petTypes = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  onCreate(regForm: NgForm): void {
    if (regForm.valid && this.petTypeId != null && this.cost > 0) {
      this.petTypeService.show(this.petTypeId).subscribe(
        data => {
          const pet = new Pet(this.name, data, this.cost);
          this.petService.store(pet).subscribe(
            data => {
              this.toastr.success('Mascota creada', 'OK', {
                timeOut: 3000
              });
              this.router.navigate(['/']);
            },
            err => {
              this.toastr.error(err.error.mensaje, 'Fail', {
                timeOut: 3000
              });
              this.router.navigate(['/']);
            }
          );
        },
        err => {
          console.log(err);
        }
      );
    } else {
      const error: AllValidationErrors = getFormValidationErrors(regForm.controls).shift();
      console.log(error);
      if (error) {
        let text;
        switch (error.control_name) {
          case 'name': text = 'Nombre obligatorio'; break;
          case 'cost': text = 'Precio obligatorios'; break;

          default: text = `${error.control_name}: ${error.error_name}: ${error.error_value}`;
        }
        this.toastr.error(text, 'Error de validación', {
          timeOut: 5000
        });
      }else if(this.petTypeId == null){
        this.toastr.error('Es necesario elegir un tipo de mascota', 'Error de validación', {
          timeOut: 5000
        });
      }else if(this.cost < 1){
        this.toastr.error('El precio debe ser mayor a 0', 'Error de validación', {
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

