import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-api3',
  templateUrl: './api3.component.html',
  styleUrl: './api3.component.css'
})
export class Api3Component {
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
