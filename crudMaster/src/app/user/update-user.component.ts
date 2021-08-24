import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Role } from '../models/role';
import { User } from '../models/user';
import { AuthService } from '../service/auth.service';
import { RoleService } from '../service/role.service';
import { TokenService } from '../service/token.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  user: User = null;

  constructor(
    private userService: UserService,
    private activateRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activateRoute.snapshot.params.id;
    this.userService.show(id).subscribe(
      data => {
        this.user = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000
        });
        this.router.navigate(['/']);
      }
    );
  }
  onUpdate(): void {
    const id = this.activateRoute.snapshot.params.id;
    delete this.user.roles;
    this.userService.update(id, this.user).subscribe(
      data => {
        console.log('se envia');
        this.toastr.success('Mascota actualizada', 'OK', {
          timeOut: 3000
        });
        this.router.navigate(['/']);
      },
      err => {
        console.log('no se envia');
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

}
