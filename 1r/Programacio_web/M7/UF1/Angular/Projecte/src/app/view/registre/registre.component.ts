import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Users} from "../../shared/classes/users";
import {emailValidator, passwordValidator} from "../directives/directives.directive";
import { LoginServiceService } from '../../shared/services/login-service.service';

@Component({
  selector: 'app-registre',
  templateUrl: './registre.component.html',
  styleUrl: './registre.component.css'
})
export class RegistreComponent {
  constructor(private connectdb : ConnectDBService, private loginService : LoginServiceService) {}

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
      //console.log(res);
      if(res == "Correu existent en la BD") {
        this.users = [];
        this.message = "Correu existent en la BD.";
      }
      else {
        this.users = res;
        console.log(this.registerForm.controls['nom'].value);
        this.message = "L'usuari " + this.registerForm.controls['nom'].value + " s'ha registrat correctament. \n Ara fes un login amb les mateixes credencials per poder començar a jugar!";
        //this.loginService.updateLoginData(res); //! Això hauria de funcionar però no ho fa. La sessió es manté però no es mostra en el menú.
      }
    })
  }
}
