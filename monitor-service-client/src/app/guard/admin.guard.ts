import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable, catchError, mergeMap, of } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):Observable<boolean> {

    return this.authService.getUserRole().pipe(
      mergeMap((role) => {
        
        if (role=="Administrator") {
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
