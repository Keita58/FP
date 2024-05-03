import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Users} from "../../shared/classes/users";

@Component({
  selector: 'app-api2users',
  templateUrl: './api2users.component.html',
  styleUrl: './api2users.component.css'
})
export class Api2usersComponent {
  constructor(private connectdb : ConnectDBService, private userBuilder : FormBuilder) {}
  data!: boolean;
  click : boolean = false;

  userForm : FormGroup = this.userBuilder.group ({
    nom : new FormControl(''),
    password : new FormControl('')
  });

  Recuperar() {
    this.connectdb.getUser(this.userForm).subscribe(res => {
      console.log(res)
      this.click = true;
      this.data = res != 0;
    })
  }
}
