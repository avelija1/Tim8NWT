import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username: string = 'bob';
  password: string = 'abc123';
  isLogged: boolean;


  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.isLogged = true;
  }
  
   login() {
	   
	this.userService.login('bob', 'abc123').subscribe(
      data => {
		const jsonRes = JSON.parse(data._body);
		localStorage.removeItem('access_token');
        localStorage.setItem('access_token', jsonRes.access_token);
        this.router.navigate(['home']);
      }
    );
    
    this.isLogged = true;
  }
  
  logout() {
	  localStorage.removeItem('access_token');
  }
  

}
