import { UserService } from '../shared/user/user.service';
 import {OnInit, Component} from '@angular/core';

 @Component({
  selector: 'app-user-list',
  templateUrl:'user-list.component.html'
})

export class UserListComponent implements OnInit {
  users: Array<any>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      this.users = data;
    });
  }
}