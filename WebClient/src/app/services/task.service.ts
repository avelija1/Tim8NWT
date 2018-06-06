import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClientModule } from '@angular/common/http';
import { Headers, Http, RequestOptionsArgs, RequestOptions } from '@angular/http';
import { StorageService } from './storage.service';
import 'rxjs/add/observable/empty';
import { Role } from '../roles/role';
import { User } from '../staff/user';
import { Task } from '../tasks/task';

@Injectable()
export class TaskService {


  token: any;

  tasksUrl:string ='//localhost:8080/api/tasks/task/';
  constructor(private http: Http, private storageService: StorageService) {
  }
  
  login(username, password): Observable<any> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization' ,  'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0');
    const opts = new RequestOptions({headers: headers});

    return this.http.post('http://localhost:8091/oauth/token?grant_type=password&username=' + 
  username + '&password='+password + '&clientId=my-trusted-client', {},{ headers: headers });
  }

  logout() {
    localStorage.removeItem('access_token');
    this.storageService.removeItem('logged');
    this.storageService.removeItem('admin');
  }

  getAllTasks(): Observable<any> {
    return this.http.get(this.tasksUrl);
  }


putTask(task: Task) {
    let url = this.tasksUrl + '/' + task.id;

    return this.http
        .put(url, task)
        .subscribe();
}

postTask(task: Task): void {
    let url = this.tasksUrl;

    this.http
        .post(url, task)
        .subscribe();
};

deleteTask(taskId: number): void {
    let url = this.tasksUrl + '/' + taskId;

    this.http
        .delete(url)
        .subscribe();
};

  checkToken(): Observable<any> {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization' ,  'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0');
    this.token = localStorage.getItem("access_token");
    if(this.token != null)
    {
      return this.http.post('http://localhost:8091/oauth/check_token?token=' + this.token + '&clientId=my-trusted-client', {},{ headers: headers });
    }
    else
      return Observable.empty<Response>();
  }
}