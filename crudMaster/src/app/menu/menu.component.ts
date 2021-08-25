import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from '../models/role';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged= false;
  public roles: string[] = [];
  isAdmin = false;
  constructor(
    private tokenService: TokenService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged= true;
      this.roles = this.tokenService.getAuthorities();
      if(this.roles.includes('ROLE_ADMIN')){
        this.isAdmin = true;
      }
    }else{
      this.isLogged = false;
    }
  }
  onLogOut():void {
    
    this.router.navigate(['/login']);
    this.tokenService.logOut();
    
  }

}
