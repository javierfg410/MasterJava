import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Pet } from '../models/pet';
import { PetType } from '../models/petType';
import { PetTypeService } from '../service/pet-type.service';
import { PetService } from '../service/pet.service';

@Component({
  selector: 'app-update-pet',
  templateUrl: './update-pet.component.html',
  styleUrls: ['./update-pet.component.css']
})

export class UpdatePetComponent implements OnInit {

  petTypes : PetType[] = [];
  pet: Pet = null;
  petTypeId: number = null;
  selected: PetType = null;

  constructor(
    private petSercive: PetService,
    private petTypeService: PetTypeService,
    private activateRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.chargePetTypes();
    const id = this.activateRoute.snapshot.params.id;
    this.petSercive.show(id).subscribe(
      data => {
        this.petTypeId = data.petType.id;
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
  onUpdate(): void {
    const id = this.activateRoute.snapshot.params.id;
    this.petSercive.update(id, this.pet).subscribe(
      data => {
        this.toastr.success('Mascota actualizada', 'OK', {
          timeOut: 3000
        });
        this.router.navigate(['/']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }
}
