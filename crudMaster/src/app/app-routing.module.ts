import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegisterComponent } from './auth/register.component';
import { IndexComponent } from './index/index.component';
import { ListPetComponent } from './pet/list-pet.component';
import { ShowPetComponent } from './pet/show-pet.component';
import { StorePetComponent } from './pet/store-pet.component';
import { UpdatePetComponent } from './pet/update-pet.component';

const routes: Routes = [
  {path: '' , component: IndexComponent},
  {path: 'login' , component: LoginComponent},
  {path: 'register' , component: RegisterComponent},
  {path: 'list', component: ListPetComponent},
  {path: 'show/:id', component: ShowPetComponent},
  {path: 'store', component: StorePetComponent},
  {path: 'update/:id', component: UpdatePetComponent},
  {path: '**', redirectTo :'',pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
