import { Component, OnInit } from '@angular/core';
import { SnackService } from '../services/snack.service';
import { StorageService } from '../services/storage.service';
import { TaskService } from '../services/task.service';
import { Task } from './task';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  displayedColumns = ['name', 'notes', 'date', 'status'];
  dataSource: any;
  datum;
  newTask:Task=new Task();

  users: any[] = [
    {firstname: 'Selma', lastname: 'Glavic', username: 'sg1', email: 'sg1@email.com', year: 4, semester: 2, courses: [
      {id: 1, name: 'TP', code: 'TP123', ects: 8.5, description: 'opiss'},
      {id: 2, name: 'IM1', code: 'IM111', ects: 9, description: 'matematika'},
      {id: 3, name: 'IM2', code: 'IM222', ects: 9.5, description: 'inzzz'}
    ]},
    {firstname: 'Amer', lastname: 'Kodzaga', username: 'akodzaga1', email: 'akodzaga1@etf.unsa.ba', year: 4, semester: 2, courses: [
    ]}
  ];

  isAdmin: boolean;

  constructor(public snackService: SnackService, public storageService:StorageService, public taskService:TaskService) { }

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

    this.taskService.getAllTasks().subscribe(data=> {if (data != null) {
      this.dataSource = data;
    }});
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
  this.newTask.name=e.data.name;
  this.newTask.notes=e.data.notes;
  this.newTask.status=e.data.status;
  this.newTask.date=e.data.date;
  this.newTask.user=e.data.user;
  this.taskService.postTask(this.newTask);
}

onRowInserted(e) {
  setTimeout(() => this.snackService.showSnack("Inserted", 'Success', 5000));
}

onRowRemoved(e) {
  this.taskService.deleteTask(e.data.id);
  setTimeout(() => this.snackService.showSnack("Deleted", 'Success', 5000));
}

onRowUpdated(e) {
  this.taskService.putTask(e.key);
  setTimeout(() => this.snackService.showSnack("Updated", 'Success', 5000));
}

}

export interface Tasks {
  name: string;
  notes: string;
  date: Date;
  status: boolean;
}

this.datum = new Date();

const ELEMENT_DATA: Tasks[] = [
  {name: 'Prvi zadatak', notes: 'Bilješke', date: this.datum, status: false},
  {name: 'Drugi zadatak', notes: 'Biiiii', date: this.datum, status: true},
  {name: 'Treci zadatak', notes: 'Buuuuu', date: this.datum, status: true}
];


