import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Api1Component} from "./view/api1/api1.component";
import {Api2Component} from "./view/api2/api2.component";
import {Api3Component} from "./view/api3/api3.component";
import {Api1usersComponent} from "./view/api1users/api1users.component";
import {Api2usersComponent} from "./view/api2users/api2users.component";
import {Api3usersComponent} from "./view/api3users/api3users.component";

const routes: Routes = [
  {path: "Api1", component: Api1Component},
  {path: "Api2", component: Api2Component},
  {path: "Api3", component: Api3Component},
  {path: "Api4", component: Api1usersComponent},
  {path: "Api5", component: Api2usersComponent},
  {path: "Api6", component: Api3usersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
