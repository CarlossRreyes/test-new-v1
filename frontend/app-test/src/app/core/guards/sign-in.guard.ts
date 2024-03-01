import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthService } from './../../features/auth/services/auth.service';
@Injectable({
  providedIn: 'root'
})

export class signInGuard implements CanActivate {

  authService = inject(AuthService);
  router = inject(Router);

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    
    const token = this.authService.token;

    if( token ){
      this.router.navigate(['/home']);
      return false;
    }

    return true;
  }

}