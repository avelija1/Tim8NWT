import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';
import { ActivityType } from '../activities/activityType';
import { ActivityService } from '../services/activity.service';
import { StorageService } from '../services/storage.service';
import { UserService } from '../services/user.service';
import { Role } from './role';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RolesComponent implements OnInit {
  

  dataSource = [];
  newRole:Role = new Role();
  isAdmin: boolean;

  constructor(public snackService: SnackService, public userService:UserService
  , public storageService:StorageService) { }

  ngOnInit() {
    this.userService.getAllRoles().subscribe(data => {
      if(data!=null){
        this.dataSource=data;
        }
    });

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
  this.newRole.name=e.data.name;
  this.userService.postRole(this.newRole);
}

onRowInserted(e) {
  setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
}

onRowRemoved(e) {
  this.userService.deleteRole(e.data.id);
  setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
}

onRowUpdated(e) {
  this.userService.putRole(e.key);
  setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
}

}

