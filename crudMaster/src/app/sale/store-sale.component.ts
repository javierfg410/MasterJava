import { DatePipe } from '@angular/common';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, NgForm, ValidationErrors,ControlValueAccessor } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../models/customer';
import { Pet } from '../models/pet';
import { Sale } from '../models/sale';
import { User } from '../models/user';
import { CustomerService } from '../service/customer.service';
import { PetService } from '../service/pet.service';
import { SaleService } from '../service/sale.service';
import { TokenService } from '../service/token.service';
import { UserService } from '../service/user.service';
import { takeUntil } from 'rxjs/operators'
import { MatSelect } from '@angular/material/select';
import { Subject } from 'rxjs';
import { Input } from '@angular/core';

@Component({
  selector: 'app-store-sale',
  templateUrl: './store-sale.component.html',
  styleUrls: ['./store-sale.component.css']
})
export class StoreSaleComponent implements OnInit {


  customers : Customer[] = [];

  public user: User = null;
  public customer: Customer = null;
  public pet: Pet= null;
  public day: number = Date.now();
  public customerDni: number = 0;

  public customerCtrl: FormControl = new FormControl();
  public customerFiltered: Customer[] = [];
  public customerFilterCtrl: FormControl = new FormControl();

  constructor(
    
    private customerService : CustomerService,
    private petService : PetService,
    private userService : UserService,
    private saleService : SaleService,

    private tokenSevice: TokenService,
    private activateRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router

  ) { }

  @ViewChild('singleSelect', { static: true }) singleSelect: MatSelect;

  protected _onDestroy = new Subject<void>();

  ngOnInit(): void {
    this.chargeCustomers();
    this.showPet();
    this.showUser();

    
    this.customerCtrl.setValue(this.customers[10]);

    this.customerFilterCtrl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.customerFilter();
      });

  }
  chargeCustomers(): void{
    this.customerService.list().subscribe(
      data => {
        this.customers = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  showPet(): void{
    const id = this.activateRoute.snapshot.params.id;
    this.petService.show(id).subscribe(
      data => {
        this.pet = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000
        });
        this.router.navigate(['/']);
      }
    );
  }
  showUser(): void{
    this.userService.findByUsername(this.tokenSevice.getUsername()).subscribe(
      data => {
        this.user = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000
        });
        this.router.navigate(['/']);
      }
    );
  }
  protected customerFilter() {
    if (!this.customers) {
      return;
    }
    // get the search keyword
    let search = this.customerFilterCtrl.value;
    if (!search) {
      this.customerFiltered = this.customers;
      return;
    } else {
      search = search.toLowerCase();
    }
    // filter the banks
    this.customerFiltered = this.customers.filter(customer => customer.dni.toLowerCase().indexOf(search) > -1);
 
  }
  onCreate(regForm: NgForm): void {
    console.log(this.customerDni);
    if (this.customerDni != 0) {
      this.customerService.show(this.customerDni).subscribe(
        data => {
          const sale = new Sale(this.user,data,this.pet,this.day);
          this.saleService.store(sale).subscribe(
            data => {
              this.toastr.success('Cliente creada', 'OK', {
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
    }else{
      const error: AllValidationErrors = getFormValidationErrors(regForm.controls).shift();

        let text;
         text = 'No has seleccionado un cliente'; 

        this.toastr.error(text, 'Error de validación', {
          timeOut: 5000
        });
      
    }

    
    //user:User,customer:Customer,pet:Pet, day:DatePipe
   
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
