import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Ex1Component } from '../../view/ex1/ex1.component';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    Ex1Component
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    Ex1Component,
  ]
})
export class ModulesModule { }
