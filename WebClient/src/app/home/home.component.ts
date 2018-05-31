import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/user/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username: string = 'bob';
  password: string = 'abc123';


  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
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
  }
  
  logout() {
	  localStorage.removeItem('access_token');
  }
  

}
