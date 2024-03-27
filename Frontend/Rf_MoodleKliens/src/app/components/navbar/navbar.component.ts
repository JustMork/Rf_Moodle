import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  authenticatedUser: User | null;


  constructor(
  private userService : UserService,
  private router : Router
  
  ){}
  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
      ).subscribe(() => {
        let currentRoute = this.router.routerState.snapshot.url
        if(!(currentRoute==='/login' || currentRoute==='/register')){
          this.loadAuthenticatedUser();
        }
      });
  }

  loadAuthenticatedUser(){
    this.userService.authenticatedUser$.subscribe(user => {
      this.authenticatedUser = user;
    });
  }



  logout(){
    this.userService.logout();
  }

}
