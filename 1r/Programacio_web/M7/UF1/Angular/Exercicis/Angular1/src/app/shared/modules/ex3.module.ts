import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Ex3Component } from '../../view/ex3/ex3.component';
import {ReactiveFormsModule} from "@angular/forms";
import { Ex3fillComponent } from '../../view/ex3fill/ex3fill.component';



@NgModule({
  declarations: [
    Ex3Component,
    Ex3fillComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    Ex3Component,
    Ex3fillComponent
  ]
})
export class Ex3Module { }
