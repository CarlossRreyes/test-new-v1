

import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthService } from './../../features/auth/services/auth.service';
@Injectable({
  providedIn: 'root'
})

export class authGuard implements CanActivate {

  authService = inject(AuthService);
  router = inject(Router);

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    
    return this.authService.verifyJwtToken()
      .pipe( tap( (isAutenticated) => {
        if( !isAutenticated ){
          localStorage.removeItem('token');
          this.router.navigate(['/auth/login'])
        }
      }))
  }

}