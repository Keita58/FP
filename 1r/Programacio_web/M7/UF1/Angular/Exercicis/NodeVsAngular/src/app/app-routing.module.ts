import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Api3Component} from "./view/api3/api3.component";
import {Api1Component} from "./view/api1/api1.component";
import {Api2Component} from "./view/api2/api2.component";

const routes: Routes = [
  {path: "Api1", component: Api1Component},
  {path: "Api2", component: Api2Component},
  {path: "Api3", component: Api3Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
