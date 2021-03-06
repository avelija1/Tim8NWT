import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClientModule } from '@angular/common/http';
import { Headers, Http, RequestOptionsArgs, RequestOptions } from '@angular/http';
import { StorageService } from './storage.service';
import 'rxjs/add/observable/empty';
import { Role } from '../roles/role';
import { User } from '../staff/user';

@Injectable()
export class UserService {


  token: any;

  usersUrl:string ='//localhost:8080/api/users/user/';
  rolesUrl:string= '//localhost:8080/api/users/role/';
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

  getAll(): Observable<any> {
    return this.http.get(this.usersUrl);
  }

  getAllRoles(): Observable<any> {
    return this.http.get(this.rolesUrl);
}

putRole(role: Role) {
    let url = this.rolesUrl + '/' + role.id;

    return this.http
        .put(url, role)
        .subscribe();
}

postRole(role: Role): void {
    let url = this.rolesUrl;

    this.http
        .post(url, role)
        .subscribe();
};

deleteRole(roleId: number): void {
    let url = this.rolesUrl + '/' + roleId;

    this.http
        .delete(url)
        .subscribe();
};


putUser(user: User) {
  let url = this.usersUrl + '/' + user.id;

  return this.http
      .put(url, user)
      .subscribe();
}

postUser(user: User): void {
  let url = this.usersUrl;

  this.http
      .post(url, user)
      .subscribe();
};

deleteUser(userId: number): void {
  let url = this.usersUrl + '/' + userId;

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