import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Course } from '../courses/course';

@Injectable()
export class CourseService {
  coursesUrl: string = "//localhost:8080/api/courses/course/"
    
  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.coursesUrl);
}

put(course: Course) {
    let url = this.coursesUrl + '/' + course.id;

    return this.http
        .put(url, course)
        .subscribe();
}

post(course: Course): void {
    let url = this.coursesUrl;

    this.http
        .post(url, course)
        .subscribe();
};

delete(courseId: number): void {
    let url = this.coursesUrl + '/' + courseId;

    this.http
        .delete(url)
        .subscribe();
};
}