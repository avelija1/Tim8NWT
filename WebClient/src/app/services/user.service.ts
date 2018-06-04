import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClientModule } from '@angular/common/http';
import { Headers, Http, RequestOptionsArgs, RequestOptions } from '@angular/http';
import { StorageService } from './storage.service';
import 'rxjs/add/observable/empty';

@Injectable()
export class UserService {


  token: any;

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
    return this.http.get('//localhost:8091/api/users/user/');
  }

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