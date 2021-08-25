import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { IndexComponent } from './index/index.component';

import { ListPetComponent } from './pet/list-pet.component';
import { ListPetTypeComponent } from './pet/list-pet-type.component';
import { ShowPetComponent } from './pet/show-pet.component';
import { StorePetComponent } from './pet/store-pet.component';
import { UpdatePetComponent } from './pet/update-pet.component';

import { StorePetTypeComponent } from './petType/store-pet-type.component';

import { ListUserComponent } from './user/list-user.component';
import { StoreUserComponent } from './user/store-user.component';
import { UpdateUserComponent } from './user/update-user.component';
import { ListHistoricUserComponent } from './user/list-historic-user.component';

import { StoreSaleComponent } from './sale/store-sale.component';

import { ListCustomerComponent } from './customer/list-customer.component';
import { StoreCustomerComponent } from './customer/store-customer.component';
import { UpdateCustomerComponent } from './customer/update-customer.component';

const routes: Routes = [
  {path: '' , component: IndexComponent},
  {path: 'login' , component: LoginComponent},
  {path: 'register' , component: RegisterComponent},

  {path: 'list', component: ListPetComponent},
  {path: 'list/:id', component: ListPetTypeComponent},
  {path: 'show/:id', component: ShowPetComponent},
  {path: 'store', component: StorePetComponent},
  {path: 'update/:id', component: UpdatePetComponent},

  {path: 'user/list', component: ListUserComponent},
  {path: 'new', component: StoreUserComponent},
  {path: 'user/update/:id', component: UpdateUserComponent},

  {path: 'petType/store', component: StorePetTypeComponent},

  {path: 'sale/:id', component: StoreSaleComponent},

  {path: 'customer/list', component: ListCustomerComponent},
  {path: 'customer/store', component: StoreCustomerComponent},
  {path: 'customer/update/:id', component: UpdateCustomerComponent},

  {path: 'historic', component: ListHistoricUserComponent},
  
  {path: '**', redirectTo :'',pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
