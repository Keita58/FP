import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ConnectDBService} from "../../services/connect-db.service";
import { Api1Component } from '../../../view/api1/api1.component';
import { Api2Component } from '../../../view/api2/api2.component';
import { Api3Component } from '../../../view/api3/api3.component';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    Api1Component,
    Api2Component,
    Api3Component
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    Api1Component,
    Api2Component,
    Api3Component
  ],
  providers: [ConnectDBService],
})
export class Ex2Module { }
