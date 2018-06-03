import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { HttpClientModule } from '@angular/common/http';
import { Headers, Http, RequestOptionsArgs, RequestOptions } from '@angular/http';
import { StorageService } from './storage.service';

@Injectable()
export class UserService {

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
  }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8091/api/users/user/');
  }
}