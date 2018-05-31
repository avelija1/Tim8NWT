import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Activity } from './activity'
@Injectable()
export class ActivityService {
    activitiesUrl: string = "//localhost:8080/api/activities/activity/"
    constructor(private http: HttpClient) {
    }

    getAll(): Observable<any> {
        return this.http.get(this.activitiesUrl);
    }

    put(activity: Activity) {
        let url = this.activitiesUrl + '/' + activity.id;

        return this.http
            .put(url, activity)
            .subscribe();
    }

    post(activity: Activity): void {
        let url = this.activitiesUrl;

        this.http
            .post(url, activity)
            .subscribe();
    };

    delete(activity: Activity): void {
        let url = this.activitiesUrl + '/' + activity.id;

        this.http
            .delete(url)
            .subscribe();
    };
}