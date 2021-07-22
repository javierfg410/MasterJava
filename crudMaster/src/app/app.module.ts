import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ListPetComponent } from './pet/list-pet.component';
import { ShowPetComponent } from './pet/show-pet.component';
import { StorePetComponent } from './pet/store-pet.component';
import { UpdatePetComponent } from './pet/update-pet.component';
import { ListUserComponent } from './user/list-user.component';
import { ShowUserComponent } from './user/show-user.component';
import { StoreUserComponent } from './user/store-user.component';
import { UpdateUserComponent } from './user/update-user.component';
import { ListCustomerComponent } from './customer/list-customer.component';
import { ShowCustomerComponent } from './customer/show-customer.component';
import { StoreCustomerComponent } from './customer/store-customer.component';
import { UpdateCustomerComponent } from './customer/update-customer.component';
import { ListSaleComponent } from './sale/list-sale.component';
import { ShowSaleComponent } from './sale/show-sale.component';
import { StoreSaleComponent } from './sale/store-sale.component';
import { UpdateSaleComponent } from './sale/update-sale.component';

import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

// external 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

import { DatePipe } from '@angular/common';
@NgModule({
  declarations: [
    AppComponent,
    ListPetComponent,
    ShowPetComponent,
    StorePetComponent,
    UpdatePetComponent,
    ListUserComponent,
    ShowUserComponent,
    StoreUserComponent,
    UpdateUserComponent,
    ListCustomerComponent,
    ShowCustomerComponent,
    StoreCustomerComponent,
    UpdateCustomerComponent,
    ListSaleComponent,
    ShowSaleComponent,
    StoreSaleComponent,
    UpdateSaleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule, 
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
