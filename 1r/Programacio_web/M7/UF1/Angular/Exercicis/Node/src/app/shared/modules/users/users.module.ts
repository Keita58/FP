import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Api1usersComponent } from '../../../view/api1users/api1users.component';
import { Api2usersComponent } from '../../../view/api2users/api2users.component';
import { Api3usersComponent } from '../../../view/api3users/api3users.component';
import {ReactiveFormsModule} from "@angular/forms";
import {ConnectDBService} from "../../services/connect-db.service";



@NgModule({
  declarations: [
    Api1usersComponent,
    Api2usersComponent,
    Api3usersComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    Api1usersComponent,
    Api2usersComponent,
    Api3usersComponent
  ],
  providers: [ConnectDBService]
})
export class UsersModule { }
