// auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable, catchError, mergeMap, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.authService.isAuth().pipe(
      mergeMap((isAuth) => {
        if (isAuth) {
          return of(true);
        } else {
          this.router.navigate(['/login']);
          return of(false);
        }
      }),
      catchError((error) => {
        console.error('Error checking authentication:', error);
        this.router.navigate(['/login']);
        return of(false); 
      })
    );
  }
}
