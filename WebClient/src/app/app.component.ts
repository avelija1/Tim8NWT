import { Component, Inject } from '@angular/core';
import DxThemes from 'devextreme/ui/themes';

import { MatSnackBar, MAT_SNACK_BAR_DATA } from "@angular/material";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(){
    // DxThemes.current('default');
  }
  title = 'app';
}


@Component({
  selector: 'info-snack-bar',
  template: '<div style="display: flex; align-items: flex-end; align-text: center;"><mat-icon>info</mat-icon><span> {{data}}</span></div>',
})
export class InfoSnackbarComponent {
  constructor(
    @Inject(MAT_SNACK_BAR_DATA) public data: any
  ) { }
}

@Component({
  selector: 'success-snack-bar',
  template: '<div style="display: flex; align-items: flex-end;"><mat-icon>done</mat-icon><span> {{data}}</span></div>',
})
export class SuccessSnackbarComponent {
  constructor(
    @Inject(MAT_SNACK_BAR_DATA) public data: any
  ) { 
  }
}

@Component({
  selector: 'error-snack-bar',
  template: '<div style="display: flex; align-items: flex-end;"><mat-icon>warning</mat-icon><span> {{data}}</span></div>',
})
export class ErrorSnackbarComponent {
  constructor(
    @Inject(MAT_SNACK_BAR_DATA) public data: any
  ) { }
}