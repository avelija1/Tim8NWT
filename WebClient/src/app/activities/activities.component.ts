import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {

  displayedColumns = ['name', 'type', 'building', 'classroom', 'course'];
  dataSource = ELEMENT_DATA;

  courses: any[] = [
    {id: 1, name: 'TP', code: 'TP123', ects: 8.5, description: 'opiss'},
    {id: 2, name: 'IM1', code: 'IM111', ects: 9, description: 'matematika'},
    {id: 3, name: 'IM2', code: 'IM222', ects: 9.5, description: 'inzzz'}
  ];

  types: any[] = [
    {value: 1, viewValue: 'Ispit'},
    {value: 2, viewValue: 'Predavanje'},
    {value: 3, viewValue: 'Tutorijal'}
  ];

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
  // this.snackService.showSnack("Inserted", 'Success', 5000);
  // this.refreshDataGrid.bind(this);
  // this._gridBoxValue = -1;
  // this._gridSelectedRowKeys = null;
}

onRowRemoved(e) {
  setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
  // this.snackService.showSnack("Removed", 'Success', 5000);
  // this.refreshDataGrid.bind(this);
}

onRowUpdated(e) {
  setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
  // this.snackService.showSnack("Updated", 'Success', 5000);
//   this.refreshDataGrid.bind(this);
//   this._gridBoxValue = -1;
//   this._gridSelectedRowKeys = null;
}

}

export interface Activities {
  name: string;
  type: number;
  building: string;
  classroom: string;
  course: number;
}

const ELEMENT_DATA: Activities[] = [
  {name: 'Prvi parcijalni', type: 1, building: 'VA', classroom: 'VA', course: 1},
  {name: 'Predavanje 1', type: 2, building: 'VA', classroom: 'VA', course: 2},
  {name: 'Integrali', type: 3, building: 'Glavna', classroom: 'S10', course: 3}
];


