import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../models/customer';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-list-customer',
  templateUrl: './list-customer.component.html',
  styleUrls: ['./list-customer.component.css']
})
export class ListCustomerComponent implements OnInit {
  customers : Customer[] = [];
  
  constructor(
    private customerService: CustomerService,
    private router: Router,
    private toastr: ToastrService,
    ) { }

  ngOnInit() {
    this.chargeCustomers();
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
  delete(Customer: Customer){
    
    this.customerService.delete(Customer.id).subscribe(
      data => {
        this.toastr.success('Cliente '+Customer.name+' eliminada', 'OK', {
          timeOut: 3000
        });
        this.chargeCustomers();
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
