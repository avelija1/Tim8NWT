import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { SnackService } from '../services/snack.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  model: any = {};
  username: string = 'bob';
  password: string = 'abc123';
  isLogged: boolean;


  constructor(private router: Router, private userService: UserService, public snackService: SnackService, 
    private storageService: StorageService) {
  }

  ngOnInit() {
    // this.userService.logout();
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
  
   login() {
	this.userService.login(this.model.username,this.model.password).subscribe(
      data => {
		const jsonRes = JSON.parse(data._body);
		localStorage.removeItem('access_token');
        localStorage.setItem('access_token', jsonRes.access_token);
        this.router.navigate(['home']);
        this.isLogged = true;
        this.storageService.setItem("logged",this.isLogged.toString());
      },
      error => {
        this.snackService.showSnack("Wrong username or password", 'Error', 5000);
    });

    
    
  }
  

  

}
