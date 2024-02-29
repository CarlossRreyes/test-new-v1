import { Component, ElementRef, Input, OnInit } from '@angular/core';


import {MenuItem} from 'primeng/api';

import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  items: MenuItem[] = [];
  isLoading: boolean = true;
  // menu: Menu[] = [];

  // private timerSubscription: Subscription | undefined;


  constructor(
    // public layoutService: LayoutService, 
    public el: ElementRef,
    // private authService: AuthService,
    // private _ts : ToastService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadPermission();    
  }

  loadPermission(){
    // this.authService.loadPermission().subscribe({
    //     next: ( resp ) => {            
    //         if( resp.status ){
    //             // console.log( resp );
    //             this.menu = resp.data;
    //             let arrayMenu: MenuItem[] = [];
    //             for( let m of this.menu ){
    //                 let arrayMenuItem: MenuItem[] = []
    //                 if( !(m.child.length == 0) ){
    //                     for( let mc of m.child ){
    //                         if( mc.id_section == m.id ){
    //                             let menuItems: MenuItem = {
    //                                 label: mc.name,
    //                                 icon: mc.icon,
    //                                 routerLink: mc.url != null ? [mc.url] : ['404']
    //                             }
    //                             arrayMenuItem.push( menuItems );
    //                         }
    //                     }
    //                 }

    //                 m.child = [];

    //                 let menu: MenuItem = {
    //                     label: m.name,
    //                     icon: m.icon,
    //                     items: arrayMenuItem
    //                 }

    //                 arrayMenu.push( menu );

    //             }
    //             this.items = arrayMenu;  
    //         }
    //         this.isLoading = false;    
    //         // console.log("Items: ", this.items );
                      
    //     },
    //     error: ( err ) => {
    //       console.log( err );
          
    //     }
    // })
  }

 

  signOut(){
    // this._ts.showWarn(' ','Cerrando Sesion');

    // this.timerSubscription = timer(1000).subscribe( () => { 
    //   this.authService.removeLocalStorage( 'token' );
    //   this.router.navigate(['/auth']); 
    // });
  }


}
