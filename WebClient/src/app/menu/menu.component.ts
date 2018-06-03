import { Component, OnInit } from '@angular/core';
import { StorageService } from '../services/storage.service';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged: boolean;

  constructor(private storageService: StorageService, private userService: UserService, private router: Router) { }

  ngOnInit() {
    if(localStorage.getItem("logged") == "true")
      this.isLogged = true;
    else
      this.isLogged = false;
    this.storageService.watchStorage().subscribe((data:string) => {
      if(localStorage.getItem("logged") == "true")
        this.isLogged = true;
      else
        this.isLogged = false;
      })
    
  }

  logout(){
    this.userService.logout();
    this.router.navigate(['home']);
  }

}
