import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';
import { CourseService } from '../services/course.service';
import { Course } from './course';
import { StorageService } from '../services/storage.service';


@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  dataSource = [];
  course: Course = new Course();
  isAdmin: boolean;
  constructor(public snackService: SnackService, public courseService: CourseService
  , public storageService: StorageService) { }

  ngOnInit() {
    this.courseService.getAll().subscribe(data => {if(data!=null){
      this.dataSource=data;
      }});

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
    this.course.name = e.data.name;
    this.course.code = e.data.code;
    this.course.description = e.data.description;
    this.course.ects = e.data.ects;
    this.course.users = null;
    this.courseService.post(this.course);
  }

  onRowInserted(e) {
    setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
    // this.snackService.showSnack("Inserted", 'Success', 5000);
    // this.refreshDataGrid.bind(this);
    // this._gridBoxValue = -1;
    // this._gridSelectedRowKeys = null;
  }

  onRowRemoved(e) {

    this.courseService.delete(e.data.id);
    setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
  }

  onRowUpdated(e) {
    this.courseService.put(e.key);
    setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
  }
}