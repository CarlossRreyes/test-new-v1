import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './features/home/layout/layout.component';

const routes: Routes = [
  { path : "", redirectTo: 'home', pathMatch : 'full'},
  {
    path: "auth",
    loadChildren: () => import("./features/auth/auth.module").then( m => m.AuthModule ),
    // canActivate: [ signInGuard ]
  },
  {
    path: "home",
    component: LayoutComponent,
    loadChildren: () => import("./features/home/home.module").then( m => m.HomeModule ),
    // canActivate: [ authGuard ]
  },

  { path : "**", redirectTo: '/auth', pathMatch : 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
