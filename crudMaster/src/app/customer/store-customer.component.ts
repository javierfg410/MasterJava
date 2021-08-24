import { Component, OnInit } from '@angular/core';
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
  onCreate(): void {

    const customer = new Customer(this.dni,this.name,this.lastname,this.address,this.country,this.email,this.phone);
    this.customerService.store(customer).subscribe(
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
  }

}
