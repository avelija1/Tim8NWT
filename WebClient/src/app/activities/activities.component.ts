import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';
import { ActivityService } from '../services/activity.service';
import { Activity } from './activity';
import { DxLookupModule } from 'devextreme-angular';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {

  dataSource = [];
  activity: Activity = new Activity();
  types: any[] = [];
  places: any[] = [];
  isAdmin: boolean;

  constructor(public snackService: SnackService, public activityService: ActivityService
    , public storageService: StorageService) { }

  ngOnInit() {
    this.activityService.getAllActivities().subscribe(data => {
      if (data != null) {
        this.dataSource = data;
      }
    });

    this.activityService.getAllActivityTypes().subscribe(data => {
      if (data != null) {
        this.types = data;
      }
    });

    this.activityService.getAllActivityPlaces().subscribe(data => {
      if (data != null) {
        this.places = data;
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
    this.activity.name = e.data.name;
    this.activity.activityType = this.types.find(x => x.id = e.data.activityType.id);
    this.activity.activityPlace = this.places.find(x => x.id = e.data.activityPlace.id);
    this.activity.course = null;
    this.activityService.postActivity(this.activity);
  }

  onRowInserted(e) {
    setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
  }

  onRowRemoved(e) {

    this.activityService.deleteActivity(e.data.id);
    setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
  }

  onRowUpdated(e) {
    this.activityService.putActivity(e.key);
    setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
  }


}



