import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Pet } from '../models/pet';
import { PetService } from '../service/pet.service';

@Component({
  selector: 'app-list-pet-type',
  templateUrl: './list-pet-type.component.html',
  styleUrls: ['./list-pet-type.component.css']
})
export class ListPetTypeComponent implements OnInit {

  pets : Pet[] = [];
  
  constructor(
    private petService: PetService,
    private router: Router,
    private toastr: ToastrService,
    private activateRoute : ActivatedRoute,
    ) { }

  ngOnInit() {
    this.chargePets();
  }

  chargePets(): void{
    const id = this.activateRoute.snapshot.params.id;
    this.petService.showType(id).subscribe(
      data => {
        this.pets = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  delete(Pet: Pet){
    
    this.petService.delete(Pet.id).subscribe(
      data => {
        this.toastr.success('Macota '+Pet.name+' eliminada', 'OK', {
          timeOut: 3000
        });
        this.chargePets();
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
