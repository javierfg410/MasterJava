import { Component, OnInit } from '@angular/core';
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

  petTypes : PetType[] = [];

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
  chargePetTypes(): void{
    this.petTypeService.list().subscribe(
      data => {
        this.petTypes = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  onCreate(): void{
    this.petTypeService.show(this.petTypeId).subscribe(
      data => {
        const pet = new Pet(this.name,data,this.cost);
        this.petService.store(pet).subscribe(
          data=>{
            this.toastr.success('Mascota creada', 'OK',{
              timeOut: 3000
            });
            this.router.navigate(['/']);
          },
          err => {
            this.toastr.error(err.error.mensaje, 'Fail',{
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
    

    
  }
}
