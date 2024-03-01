import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subscription, timer } from 'rxjs';
import { AuthService } from 'src/app/features/auth/services/auth.service';
// import { AuthService } from 'src/app/features/auth/service/auth.service';

import { LayoutService } from 'src/app/features/home/services/layout.service';
import { ToastService } from 'src/app/utils/toast.service';
// import { ToastService } from 'src/app/utils/toast.service';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss'],
  // providers : [MessageService, ToastService  ],

})
export class TopbarComponent implements OnInit, OnDestroy {
  items!: [];

  @ViewChild('menubutton') menuButton!: ElementRef;

  @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

  @ViewChild('topbarmenu') menu!: ElementRef;

  private timerSubscription: Subscription | undefined;

  constructor(
    public layoutService: LayoutService,
    private authService: AuthService,
    private router: Router,
    private _ts : ToastService,

  ) { }
    

  ngOnInit(): void {
    
    
  }

  ngOnDestroy(): void {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }


  signOut(){
    this._ts.showWarn(' ','Cerrando Sesion');

    this.timerSubscription = timer(1000).subscribe( () => { 
      this.authService.removeLocalStorage( 'token' );
      this.router.navigate(['/auth']); 
    });
  }

}
