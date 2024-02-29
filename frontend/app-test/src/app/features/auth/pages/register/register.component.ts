import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterUser } from '../../interfaces/auth';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit{
  formUser!: FormGroup;


  constructor(
    private fb: FormBuilder
  ){

  }

  ngOnInit(): void {
    this.initForm();
  }


  initForm() {
    this.formUser = this.fb.group({
      identificacion: ['', [Validators.required]],
      nombres: ['', [Validators.required]],
      apellidos: ['', [Validators.required]],
      contrasena: ['', [Validators.required]],
      fechaNacimiento: ['', [Validators.required]],
    });
  }

  fieldsValidate(campo: string){
    return this.formUser.get(campo)?.invalid && this.formUser.get(campo)?.touched && this.formUser.get(campo)?.dirty;
  }

  saveUser(){
    if(this.formUser.invalid){      
      return 
    };


    const form: RegisterUser  = this.formUser.value
    console.log("Objeto: ", form );
    


  }


}
