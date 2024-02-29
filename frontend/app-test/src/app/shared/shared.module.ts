import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { TopbarComponent } from './components/topbar/topbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { PrimeNgModule } from './prime-ng/prime-ng.module';



@NgModule({
  declarations: [
    SidebarComponent,
    TopbarComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    PrimeNgModule
  ], 
  exports: [
    SidebarComponent,
    TopbarComponent,
    FooterComponent,
    PrimeNgModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule { }
