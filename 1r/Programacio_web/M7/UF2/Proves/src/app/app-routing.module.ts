import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Exercici1Component} from "./view/exercici1/exercici1.component";
import {TriangleComponent} from "./view/triangle/triangle.component";
import {ComptarComponent} from "./view/comptar/comptar.component";
import {SumaComponent} from "./view/suma/suma.component";

const routes: Routes = [
  {path: "Ex1", component: Exercici1Component},
  {path: "Tri", component: TriangleComponent},
  {path: "Com", component: ComptarComponent},
  {path: "Sum", component: SumaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
