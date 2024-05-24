import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Exercici1Component} from "./view/exercici1/exercici1.component";
import {TriangleComponent} from "./view/triangle/triangle.component";

const routes: Routes = [
  {path: "Ex1", component: Exercici1Component},
  {path: "Tri", component: TriangleComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
