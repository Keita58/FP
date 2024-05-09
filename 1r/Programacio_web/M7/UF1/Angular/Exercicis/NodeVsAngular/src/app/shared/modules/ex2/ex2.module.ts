import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from '../../../view/login/login.component';
import { ValidacioDirective } from '../../../view/login/validacio.directive';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    LoginComponent,
    ValidacioDirective
  ],
    imports: [
        CommonModule,
        ReactiveFormsModule
    ],
  exports: [
    LoginComponent,
    ValidacioDirective
  ]
})
export class Ex2Module { }
