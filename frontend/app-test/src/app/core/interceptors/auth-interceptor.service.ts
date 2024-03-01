import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './../../features/auth/services/auth.service';
// import { MessageHandlerService } from 'src/app/utils/message-handler.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(
    private service: AuthService,
    // private _mh : MessageHandlerService

  ) { }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>  {
    const token = this.service.token;
    if( token ){
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });

      //**********IMplements validations************/
      return next.handle(cloned)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if( error.status === 404 ){            
            console.log("Error 404 o por definir");          
            return EMPTY;
          }
          console.log("OBJETO ERROR: ", error);

          if ( error.status === 422 ) {
            if ( error.error.fails && error.error.fails.errors_pais ) {
              // this._mh.sendMessage(error.error.fails.errors_pais.nombre_pais[0]);
              return EMPTY;
            }
          }
          
          return throwError(() => new Error(`${error.error.message} `))
          // return throwError(() => new Error(`${error.error.message} - ${error.error.error}`)) //TODO: VER EL ERRORA A TODOS LOS COMPONENTES
        })
      );
    }
    return next.handle(req);  
  }
}
