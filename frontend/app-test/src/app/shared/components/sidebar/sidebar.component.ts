import { Component, ElementRef, Input, OnInit } from '@angular/core';


import {MenuItem} from 'primeng/api';

import { Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  items: MenuItem[] = [];
  isLoading: boolean = true;
  menu: any[] = [];

  // private timerSubscription: Subscription | undefined;


  constructor(
    // public layoutService: LayoutService, 
    public el: ElementRef,
    private authService: AuthService,
    // private _ts : ToastService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadPermission();    
  }

  loadPermission(){
    this.authService.loadPermission().subscribe({
       next: ( resp ) => {
        console.log(resp);
        this.menu = resp.data;
        let arrayMenu: MenuItem[] = []
        for( let m of this.menu ){
          let menu: MenuItem = {
            label: m.opcion.name,
            
          }

          arrayMenu.push( menu );

        }

        this.items = arrayMenu;  

        this.isLoading = false;  
       }
        
    })
  }

 

  signOut(){
    // this._ts.showWarn(' ','Cerrando Sesion');

    // this.timerSubscription = timer(1000).subscribe( () => { 
    //   this.authService.removeLocalStorage( 'token' );
    //   this.router.navigate(['/auth']); 
    // });
  }


}
