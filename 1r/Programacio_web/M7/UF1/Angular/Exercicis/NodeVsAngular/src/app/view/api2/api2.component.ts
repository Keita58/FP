import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-api2',
  templateUrl: './api2.component.html',
  styleUrl: './api2.component.css'
})
export class Api2Component {
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
