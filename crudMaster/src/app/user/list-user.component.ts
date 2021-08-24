import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../models/user';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users : User[] = [];

  constructor(
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService,
  ) { }

  ngOnInit(): void {
    this.chargeUsers();
  }
  chargeUsers(): void{
    this.userService.list().subscribe(
      data => {
        this.users = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  delete(User: User){
    
    this.userService.delete(User.id).subscribe(
      data => {
        this.toastr.success('Usuario '+User.name+' '+User.lastname+' eliminado', 'OK', {
          timeOut: 3000
        });
        this.chargeUsers();
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
