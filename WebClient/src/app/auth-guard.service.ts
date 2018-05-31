import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor() {
  }

  canActivate(): boolean {
    const token = localStorage.getItem('access_token');
    return !!token;

  }
}
