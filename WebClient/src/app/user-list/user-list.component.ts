import { UserService } from '../shared/user/user.service';
 import {OnInit, Component} from '@angular/core';
 

 @Component({
  selector: 'app-user-list',
  templateUrl:'user-list.component.html'
})

export class UserListComponent implements OnInit {
  users: Array<any>;
// ima li upste login??
// I nisam nigdje vidio da se cuva token u local storage, nisu implementirali kolege jos login i nismo sacuvali nigdje i ne znamo kako
// trebali bi testirati u postmanu
// Treba im token store, prvo da cuva u local storege access_token koji se poslije selje u svakom requestu
// u Headeru Key: Authentication  Value : Bearer <toke Value>, a to se dobije sa Auth Servisa
// mozes li to sada napraviti
// Je li ti radi user service?
// radio je, testiran je i on u postmanu
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      this.users = data;
    console.log(this.users[0]);
    });
  }
}