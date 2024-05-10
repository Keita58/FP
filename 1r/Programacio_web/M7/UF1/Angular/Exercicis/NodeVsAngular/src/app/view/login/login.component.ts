import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Users} from "../../shared/classes/users";
import {passwordValidator, ValidacioDirective} from "./validacio.directive";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private connectdb : ConnectDBService) {}

  loginForm! : FormGroup;
  users : Users[] = [];
  message! : string;

  ngOnInit() : void {
    this.loginForm = new FormGroup({
      nom : new FormControl('', [Validators.required, Validators.minLength(3)]),
      password : new FormControl('', [Validators.required, Validators.minLength(5), passwordValidator()])
    })
  }

  login() : void {
    this.connectdb.getUser(this.loginForm).subscribe(res => {
      console.log(res);
      if(res == 0) {
        this.users = [];
        this.message = "Login incorrecte";
      }
      else {
        this.users = res;
        console.log(this.loginForm.controls['nom'].value);
        this.message = "L'usuari " + this.loginForm.controls['nom'].value + " s'ha logejat correctament.";
      }
    })
  }
}
