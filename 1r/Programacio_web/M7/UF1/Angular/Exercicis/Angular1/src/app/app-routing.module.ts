import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Ex2Component} from "./view/ex2/ex2.component";
import {Ex1Component} from "./view/ex1/ex1.component";
import {Ex3Component} from "./view/ex3/ex3.component";

const routes: Routes = [
  {path: "Ex1", component: Ex1Component},
  {path: "Ex2", component: Ex2Component},
  {path: "Ex3", component: Ex3Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
