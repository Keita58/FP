import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Users} from "../../shared/classes/users";
import {emailValidator, passwordValidator} from "../login/validacio.directive";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private connectdb : ConnectDBService) {}

  registerForm! : FormGroup;
  users : Users[] = [];
  message! : string;

  ngOnInit() : void {
    this.registerForm = new FormGroup({
      nom : new FormControl('', [Validators.required, Validators.minLength(7), emailValidator()]),
      password : new FormControl('', [Validators.required, Validators.minLength(8), passwordValidator()])
    })
  }

  register() : void {
    this.connectdb.registerUser(this.registerForm).subscribe(res => {
      console.log(res);
      if(res == "Correu existent en la BD") {
        this.users = [];
        this.message = "Correu existent en la BD.";
      }
      else {
        this.users = res;
        console.log(this.registerForm.controls['nom'].value);
        this.message = "L'usuari " + this.registerForm.controls['nom'].value + " s'ha registrat correctament.";
      }
    })
  }
}
