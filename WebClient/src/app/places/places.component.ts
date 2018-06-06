import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';
import { ActivityPlace } from '../activities/activityPlace';
import { ActivityService } from '../services/activity.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-places',
  templateUrl: './places.component.html',
  styleUrls: ['./places.component.css']
})
export class PlacesComponent implements OnInit {
  dataSource = [];
  newActivityPlace:ActivityPlace = new ActivityPlace();
  isAdmin: boolean;

  constructor(public snackService: SnackService, public activityService:ActivityService
  , public storageService:StorageService) { }

  ngOnInit() {
    this.activityService.getAllActivityPlaces().subscribe(data => {
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
  this.newActivityPlace.buildingName=e.data.buildingName;
  this.newActivityPlace.classRoomName=e.data.classRoomName;
  console.log(this.newActivityPlace);
  this.activityService.postActivityPlace(this.newActivityPlace);
}


onRowInserted(e) {
  setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
}

onRowRemoved(e) {
  this.activityService.deleteActivityPlace(e.data.id);
  setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
}

onRowUpdated(e) {
  this.activityService.putActivityPlace(e.key);
  setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
}
}