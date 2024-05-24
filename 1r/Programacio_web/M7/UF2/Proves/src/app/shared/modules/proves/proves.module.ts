import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Exercici1Component } from '../../../view/exercici1/exercici1.component';
import {ReactiveFormsModule} from "@angular/forms";
import { TriangleComponent } from '../../../view/triangle/triangle.component';



@NgModule({
  declarations: [
    Exercici1Component,
    TriangleComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    Exercici1Component,
    TriangleComponent
  ]
})
export class ProvesModule { }
