import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Ex2Component } from '../../view/ex2/ex2.component';
import {ReactiveFormsModule} from "@angular/forms";
import { Ex2fillComponent } from '../../view/ex2fill/ex2fill.component';



@NgModule({
  declarations: [
    Ex2Component,
    Ex2fillComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    Ex2Component,
    Ex2fillComponent
  ]
})
export class Ex2Module { }
