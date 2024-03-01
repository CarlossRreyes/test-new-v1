import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, catchError, of, take, tap } from 'rxjs';
import { Credentials } from '../interfaces/auth';
import { JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  api = environment.apiUrl;

  constructor(
    private http: HttpClient,
    private jwtHelper:JwtHelperService
  ) { }

  verifyJwtToken(): Observable<boolean> {
    if( this.jwtHelper.isTokenExpired( this.token ) || !this.token ) { return of( false ); }
    return of( true );
  }

  signIn( credentials: Credentials ) {
    const url = `${this.api}/auth/login`;
    return this.http.post<any>( url, credentials )
      .pipe(
        // catchError(this.errorHandilings.handleHttpError), 
        take(1),
        tap( ( resp ) => { 
          console.log(resp);
          
          if( resp.status ){
            this.setLocalStorage('token', resp.token);
          }
      }));
  }


  setLocalStorage( key:string, token:string ){
    localStorage.setItem(key, JSON.stringify(token));
  }

  removeLocalStorage( key:string ){
    localStorage.removeItem(key);
  }

  get token(): string {
    return JSON.parse(localStorage.getItem('token')!) || '';
  } 

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  loadPermission(){
    const url = `${this.api}/security/permission/userAuthenticate`;
    return this.http.get<any>( url );
  }


}
