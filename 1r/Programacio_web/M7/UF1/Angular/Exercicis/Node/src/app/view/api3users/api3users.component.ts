import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-api3users',
  templateUrl: './api3users.component.html',
  styleUrl: './api3users.component.css'
})
export class Api3usersComponent {
  constructor(private connectdb : ConnectDBService, private form : FormBuilder) {}

  resultat! : string;

  userForm : FormGroup = this.form.group({
    nom : new FormControl(''),
    password : new FormControl('')
  })

  Guardar() {
    this.connectdb.registerUser(this.userForm).subscribe(res => {
      this.resultat = res;
    })
  }
}
