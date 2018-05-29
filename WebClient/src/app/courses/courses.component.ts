import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';


@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  displayedColumns = ['name', 'code', 'ects', 'description'];
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

export interface Courses {
  name: string;
  code: string;
  ects: number;
  description: string;
}

const ELEMENT_DATA: Courses[] = [
  {name: 'TP', code: 'TP123', ects: 8.5, description: 'opiss'},
  {name: 'IM1', code: 'IM111', ects: 9, description: 'matematika'},
  {name: 'IM2', code: 'IM222', ects: 9.5, description: 'inzzz'}
];
