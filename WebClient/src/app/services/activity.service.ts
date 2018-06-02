import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Activity } from '../activities/activity'
import { ActivityType } from '../types/activityType';
import { ActivityPlace } from '../places/activityPlace';
@Injectable()
export class ActivityService {
    activitiesUrl: string = "//localhost:8080/api/activities/activity/"
    activityPlacesUrl: string = "//localhost:8080/api/activities/activityPlace/"
    activityTypesUrl: string = "//localhost:8080/api/activities/activityType/"
    constructor(private http: HttpClient) {
    }

    getAllActivities(): Observable<any> {
        return this.http.get(this.activitiesUrl);
    }

    putActivity(activity: Activity) {
        let url = this.activitiesUrl + '/' + activity.id;

        return this.http
            .put(url, activity)
            .subscribe();
    }

    postActivity(activity: Activity): void {
        let url = this.activitiesUrl;

        this.http
            .post(url, activity)
            .subscribe();
    };

    deleteActivity(activityId: number): void {
        let url = this.activitiesUrl + '/' + activityId;

        this.http
            .delete(url)
            .subscribe();
    };


    getAllActivityTypes(): Observable<any> {
        return this.http.get(this.activityTypesUrl);
    }

    putActivityType(activityType: ActivityType) {
        let url = this.activityTypesUrl + '/' + activityType.id;

        return this.http
            .put(url, activityType)
            .subscribe();
    }

    postActivityType(activityType: ActivityType): void {
        let url = this.activityTypesUrl;

        this.http
            .post(url, activityType)
            .subscribe();
    };

    deleteActivityType(activityTypeId: number): void {
        let url = this.activityTypesUrl + '/' + activityTypeId;

        this.http
            .delete(url)
            .subscribe();
    };

    getAllActivityPlaces(): Observable<any> {
        return this.http.get(this.activityPlacesUrl);
    }

    putActivityPlace(activityPlace: ActivityPlace) {
        let url = this.activityPlacesUrl + activityPlace.id;

        return this.http
            .put(url, activityPlace)
            .subscribe();
    }

    postActivityPlace(activityPlace: ActivityPlace): void {
        let url = this.activityPlacesUrl;

        this.http
            .post(url, activityPlace)
            .subscribe();
    };

    deleteActivityPlace(activityPlaceId): void {
        let url = this.activityPlacesUrl  + activityPlaceId;

        this.http
            .delete(url)
            .subscribe();
    };
}