import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';
import { StorageService } from '../services/storage.service';
import { User } from './user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {

  displayedColumns = ['firstname', 'lastname', 'username', 'email'];
  dataSource = ELEMENT_DATA;
  isAdmin: boolean;
  newStaffMember:User=new User();
  courses: any[] = [
    {id: 1, name: 'TP', code: 'TP123', ects: 8.5, description: 'opiss'},
    {id: 2, name: 'IM1', code: 'IM111', ects: 9, description: 'matematika'},
    {id: 3, name: 'IM2', code: 'IM222', ects: 9.5, description: 'inzzz'}
  ];

  constructor(public snackService: SnackService, public storageService: StorageService, public userService:UserService) { }

  ngOnInit() {
    if(localStorage.getItem("admin") == "true")
    this.isAdmin = true;
  else
    this.isAdmin = false;
  this.storageService.watchStorage().subscribe((data:string) => {
    if(localStorage.getItem("admin") == "true")
      this.isAdmin = true;
    else
      this.isAdmin = false;
    });

   // this.userService.getAll().subscribe(data=>this.dataSource=data);
  }

  onCellPrepared(e) {
    if (e.rowType === 'data' && e.column.command === 'edit') {
        var isEditing = e.row.isEditing,
            cellElement = e.cellElement;
 
        if (isEditing) {
            let saveLink = cellElement.querySelector('.dx-link-save'),
                cancelLink = cellElement.querySelector('.dx-link-cancel');
 
            saveLink.classList.add('dx-icon-save');
            cancelLink.classList.add('dx-icon-revert');
 
            saveLink.textContent = '';
            cancelLink.textContent = '';
        } else {
            let editLink = cellElement.querySelector('.dx-link-edit'),
                deleteLink = cellElement.querySelector('.dx-link-delete');
 
            editLink.classList.add('dx-icon-edit');
            deleteLink.classList.add('dx-icon-trash');
 
            editLink.textContent = '';
            deleteLink.textContent = '';
        }
    }
  }
  
  onContentReady(e) {
    e.component.columnOption('command:edit', {
       visibleIndex: -1,
       width: 60
   });
}

onRowInserting(e) {
  e.data.courses=[];
  this.newStaffMember.firstName=e.data.firstname;
  this.newStaffMember.lastName=e.data.lastname;
  this.newStaffMember.userName=e.data.username;
  this.newStaffMember.email=e.data.email;

  this.userService.postUser(this.newStaffMember);
}


onRowInserted(e) {
  setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
}

onRowRemoved(e) {
  this.userService.deleteUser(e.data.id);
  setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
}

onRowUpdated(e) {
  this.userService.putUser(e.key);
  setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));

}
}

export interface Staff {
  firstname: string;
  lastname: string;
  username: string;
  email: string;
  courses: any[];
}

const ELEMENT_DATA: Staff[] = [
  {firstname: 'Lejla', lastname: 'Bajgoric', username: 'lb1', email: 'lb1@email.com', courses: [
    {id: 1, name: 'TP', code: 'TP123', ects: 8.5, description: 'opiss'},
    {id: 2, name: 'IM1', code: 'IM111', ects: 9, description: 'matematika'},
    {id: 3, name: 'IM2', code: 'IM222', ects: 9.5, description: 'inzzz'}
  ]},
  {firstname: 'Amer', lastname: 'Kodzaga', username: 'akodzaga1', email: 'akodzaga1@etf.unsa.ba', courses: [
  ]}
];