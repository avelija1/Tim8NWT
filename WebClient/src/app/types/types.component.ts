import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';
import { ActivityType } from '../activities/activityType';
import { ActivityService } from '../services/activity.service';

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.css']
})
export class TypesComponent implements OnInit {
  

  dataSource;
  newActivityType:ActivityType = new ActivityType();

  constructor(public snackService: SnackService, public activityService:ActivityService) { }

  ngOnInit() {
    this.dataSource=this.activityService.getAllActivityTypes().subscribe(data => {
      if(data!=null){
      this.dataSource=data;
      }
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
  this.newActivityType.name=e.data.name;
  this.activityService.postActivityType(this.newActivityType);
}


onRowInserted(e) {
  setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
}

onRowRemoved(e) {
  this.activityService.deleteActivityType(e.data.id);
  setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
}

onRowUpdated(e) {
  this.activityService.putActivityType(e.key);
  setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
}

}

