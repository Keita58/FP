import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Users} from "../../shared/classes/users";
import {ConnectDBService} from "../../shared/services/connect-db.service";
import { LoginServiceService } from '../../shared/services/login-service.service';

/**
 * Component del login dels jugadors
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  ////@Input() jugadorPuntuacioNova : Users | undefined;
  ////@Output() envia : EventEmitter<Users> = new EventEmitter<Users>();
  /**
   * Constructor base que inicia les variables connectdb i loginService. La primera l'utilitzarem per a la connexió amb la base de dades i poder verificar que 
   * les dades per al login son correctes i la segona per a mantenir la sessió durant tota l'estona dins la pàgina (i poder mostrar tota l'estona amb quin
   * usuari està loguejat).
   * @param connectdb 
   * @param loginService 
   */
  constructor(private connectdb : ConnectDBService, private loginService : LoginServiceService) {}

  /**
   * @ignore
   */
  loginForm! : FormGroup;

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
    this.loginForm = new FormGroup({
      nom : new FormControl('', [Validators.required, Validators.minLength(7)]),
      password : new FormControl('', [Validators.required, Validators.minLength(8)])
    })
  }

  /**
   * Amb aquesta funció el que fem és enviar les dades del formulari que el jugador omple a la funció getUser de {@link ConnectDBService} que ens retorna si les 
   * dades introduïdes existeixen. Si la funció no ens retorna res vol dir que les dades són incorrectes i ho notifiquem a l'usuari amb un missatge per pantalla.
   * Si són correctes també ho notifiquem amb un missatge i afegim el correu de l'usuari a la variable loginService esmenada anteriorment, que ens facilitarà poder 
   * mostrar mostrar quin usuari està loguejat en cada moment.
   * 
   * @returns void
   */
  login() : void {
    this.connectdb.getUser(this.loginForm).subscribe(res => {
      console.log(res);
      if(res == 0) {
        this.users = [];
        this.message = "Login incorrecte";
      }
      else {
        this.users = res;
        console.log(this.users);
        this.message = "L'usuari " + this.loginForm.controls['nom'].value + " s'ha logejat correctament.";
        ////this.envia.emit(new Users(this.users[0].nom, this.users[0].password, this.users[0].punts));
        this.loginService.updateLoginData(res); //* Passem la info al loginService per a que tingui l'usuari tota l'estona
      }
    })
  }
}
