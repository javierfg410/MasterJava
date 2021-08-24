import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PetTypeService } from '../service/pet-type.service';
import { PetType } from '../models/petType';

@Component({
  selector: 'app-store-pet-type',
  templateUrl: './store-pet-type.component.html',
  styleUrls: ['./store-pet-type.component.css']
})
export class StorePetTypeComponent implements OnInit {

  name: string = '';

  constructor(
    private petTypeService: PetTypeService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {

  }
  onCreate(): void {
    const petType = new PetType(this.name);
    this.petTypeService.store(petType).subscribe(
      data => {
        this.toastr.success('Mascota creada', 'OK', {
          timeOut: 3000
        });
        this.router.navigate(['/store']);
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
