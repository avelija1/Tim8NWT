import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';

@Component({
  selector: 'app-places',
  templateUrl: './places.component.html',
  styleUrls: ['./places.component.css']
})
export class PlacesComponent implements OnInit {
  displayedColumns = [ 'building', 'classroom'];
  dataSource = ELEMENT_DATA;


  constructor(public snackService: SnackService) { }

  ngOnInit() {
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

onRowInserted(e) {
  setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
}

onRowRemoved(e) {
  setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
}

onRowUpdated(e) {
  setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
}

}

export interface Places {
  building: string;
  classroom: string;
}

const ELEMENT_DATA: Places[] = [
  {building: 'VA', classroom: 'VA'},
  {building: 'VA', classroom: 'VA'},
  {building: 'Glavna', classroom: 'S10'}
];
