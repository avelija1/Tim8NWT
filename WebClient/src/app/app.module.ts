import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { UserService } from './shared/user/user.service';
import {UserListComponent} from './user-list/user-list.component';

import { AppComponent, InfoSnackbarComponent, ErrorSnackbarComponent, SuccessSnackbarComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
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

const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'activities', component: ActivitiesComponent },
  { path: 'staff', component: StaffComponent },
  { path: 'students', component: StudentsComponent }
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
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [UserService, SnackService],
  entryComponents:[
    InfoSnackbarComponent,
    SuccessSnackbarComponent,
    ErrorSnackbarComponent    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
