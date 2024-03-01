import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { AuthInterceptorService } from './core/interceptors/auth-interceptor.service';
import { MessageService } from 'primeng/api';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true
    },
    { 
      provide: JWT_OPTIONS, useValue: JWT_OPTIONS 
    },
    JwtHelperService,
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
