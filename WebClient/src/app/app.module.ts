import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { UserService } from './shared/user/user.service';
import {UserListComponent} from './user-list/user-list.component';
import { HttpModule } from '@angular/http';
import { AppComponent, InfoSnackbarComponent, ErrorSnackbarComponent, SuccessSnackbarComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { MatMenuModule, MatButtonModule, MatIconModule, MatCardModule, MatSidenavModule, MatTableModule, MatTableDataSource, MatSnackBarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { CoursesComponent } from './courses/courses.component';
import { RouterModule, Routes } from '@angular/router';
import { ActivitiesComponent } from './activities/activities.component';
import { StaffComponent } from './staff/staff.component';
import { StudentsComponent } from './students/students.component';
import { DxDataGridModule } from 'devextreme-angular';
import { SnackService } from './services/snack.service';
import {  DxScrollViewModule  } from 'devextreme-angular/ui/scroll-view';
import {AuthInterceptor} from './auth.interceptor';
import {AuthGuard} from './auth-guard.service';
import { ActivityService } from './activities/activity.service';

const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'courses', component: CoursesComponent, canActivate: [AuthGuard] },
  { path: 'activities', component: ActivitiesComponent, canActivate: [AuthGuard] },
  { path: 'staff', component: StaffComponent, canActivate: [AuthGuard] },
  { path: 'students', component: StudentsComponent, canActivate: [AuthGuard] }
]

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    MenuComponent,
    HomeComponent,
    CoursesComponent,
    ActivitiesComponent,
    StaffComponent,
    StudentsComponent,
    InfoSnackbarComponent,
    SuccessSnackbarComponent,
    ErrorSnackbarComponent
    
  ],
  imports: [
  HttpModule,
    BrowserModule,
    HttpClientModule,
    MatButtonModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    DxDataGridModule,
    MatSnackBarModule,
    DxScrollViewModule,
    RouterModule.forRoot(
      appRoutes,
     
    )
  ],
  providers: [
	UserService, 
	SnackService, 
	AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
	],
  entryComponents:[
    InfoSnackbarComponent,
    SuccessSnackbarComponent,
    ErrorSnackbarComponent    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
