import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from '../../../view/login/login.component';
import { ValidacioDirective } from '../../../view/login/validacio.directive';
import {ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from '../../../view/register/register.component';



@NgModule({
  declarations: [
    LoginComponent,
    ValidacioDirective,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    LoginComponent,
    ValidacioDirective,
    RegisterComponent
  ]
})
export class Ex2Module { }
