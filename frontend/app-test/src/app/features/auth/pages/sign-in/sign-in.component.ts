import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

import { Subscription, timer } from 'rxjs';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Credentials } from '../../interfaces/auth';
import { RegisterComponent } from '../register/register.component';
import { AuthService } from '../../services/auth.service';
import { ToastService } from 'src/app/utils/toast.service';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent implements OnInit, OnDestroy {

  signInForm!: FormGroup;
  hide : boolean = true;
  ref: DynamicDialogRef | undefined;

  private subscription: Subscription | undefined;


  constructor(
    public formBuilder: FormBuilder,
    private router: Router,
    private service: AuthService,
    private _ts : ToastService,
    public dialogService: DialogService,
  ){ }
  
  ngOnInit(): void {
    this.initForm();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
  
  initForm(){
    this.signInForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  fieldsValidate(campo: string){
    return this.signInForm.get(campo)?.invalid && this.signInForm.get(campo)?.touched && this.signInForm.get(campo)?.dirty;
  }

  signIn(){
    this.signInForm.markAllAsTouched();
    if (this.signInForm.invalid) { return; }
    
    if (this.signInForm.valid) {
      const form : Credentials = this.signInForm.value; 
      this.signInService(form);
    }
  }
  
  signInService( data: Credentials){
    console.log(data);
    
    this.subscription = this.service.signIn( data ).subscribe({
      next: ( resp ) => {
        if( resp.status ){
          this._ts.showSuccess('Inicio de Sesion',resp.message);
        } else {
          console.log(resp);
          
          this._ts.showError('Inicio de Sesion',resp.message);
        }
      }, 
      error: ( err ) => {
        console.log(err);
        
        this._ts.showError('Inicio de Sesion',err.error.message);
        
      },
      complete: () => { 
        this.subscription = timer(1000).subscribe( () => { this.router.navigate(['/home']); });
      }
    })
  }

  register(){
    this.ref = this.dialogService.open( RegisterComponent, {
      header: 'Registro de usuarios',      
      width: '50vw',
      contentStyle: {
        styles: [
            {
                breakpoint: '960px',
                mediaQuery: '(max-width: 960px)',
                width: '75vw'
            },
            {
                breakpoint: '640px',
                mediaQuery: '(max-width: 640px)',
                width: '90vw'
            }
        ]
      },
      baseZIndex: 10000,
      maximizable: false,
      modal: true,
      
      // styleClass: "",
      
      closeOnEscape: false
    });

    // this.ref.onClose.subscribe((value) => { 
    //   if (value == undefined) {
    //     return;
    //   }
  
    //   if (value.status) {
    //     this._ts.showSuccess('Mensaje',value.message);
    //   }
    // });


  }







}
