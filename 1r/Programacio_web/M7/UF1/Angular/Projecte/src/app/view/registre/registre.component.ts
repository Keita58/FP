import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Users} from "../../shared/classes/users";
import {emailValidator, passwordValidator} from "../directives/directives.directive";
import { LoginServiceService } from '../../shared/services/login-service.service';

/**
 * Component del registre dels jugadors
 */
@Component({
  selector: 'app-registre',
  templateUrl: './registre.component.html',
  styleUrl: './registre.component.css'
})
export class RegistreComponent {
  /**
   * @ignore
   */
  constructor(private connectdb : ConnectDBService, private loginService : LoginServiceService) {}

  /**
   * @ignore
   */
  registerForm! : FormGroup;

  /**
   * @ignore
   */
  users : Users[] = [];

  /**
   * @ignore
   */
  message! : string;

  /**
   * @ignore
   */
  ngOnInit() : void {
    this.registerForm = new FormGroup({
      nom : new FormControl('', [Validators.required, Validators.minLength(7), emailValidator()]),
      password : new FormControl('', [Validators.required, Validators.minLength(8), passwordValidator()])
    })
  }

  /**
   * Amb aquesta funció el que fem és enviar les dades del formulari que el jugador omple a la funció registerUser de {@link ConnectDBService} que ens retorna si
   * les dades introduïdes existeixen. Si la funció ens retorna un missatge de que la informació del formulari ja existeix retornem el mateix a l'usuari
   * i no deixem que es registri. En canvi, si retorna la informació del formulari vol dir que el registre és correcte i ho notifiquem a l'usuari per pantalla
   * (a part d'avisar-lo que s'ha de loguejar per poder jugar).
   *
   * @returns void
   */
  register() : void {
    this.connectdb.registerUser(this.registerForm).subscribe(res => {
      //console.log(res);
      if(res == "Correu existent en la BD") {
        this.users = [];
        this.message = "Correu existent en la BD.";
      }
      else {
        //this.users = res[0]; //Això es un "Afegida!"
        console.log(this.registerForm.controls['nom'].value);
        this.message = "L'usuari " + this.registerForm.controls['nom'].value + " s'ha registrat correctament. \n Ara fes un login amb les mateixes credencials per poder començar a jugar!";
        //this.loginService.updateLoginData(res); //! Això hauria de funcionar però no ho fa. La sessió es manté però no es mostra en el menú.
      }
    })
  }
}
