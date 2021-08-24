import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Pet } from '../models/pet';
import { User } from '../models/user';
import { PetService } from '../service/pet.service';
import { TokenService } from '../service/token.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-list-historic-user',
  templateUrl: './list-historic-user.component.html',
  styleUrls: ['./list-historic-user.component.css']
})
export class ListHistoricUserComponent implements OnInit {

  user: User = null;
  pets: Pet[] = [];
  total: number = 0;

  constructor(
    private userService: UserService,
    private petService: PetService,
    private activateRoute: ActivatedRoute,
    private tokenSevice: TokenService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.showUser();
    

  }
  showUser(): void{
    this.userService.findByUsername(this.tokenSevice.getUsername()).subscribe(
      data => {
        this.user = data;
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
  chargePets(): void{
    this.petService.user(this.user.id).subscribe(
      data => {
        this.pets = data;
        this.pets.forEach(childObj=> {
          this.total += childObj.cost;
       });
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
