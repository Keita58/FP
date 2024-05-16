import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Users} from "../../shared/classes/users";
import {ConnectDBService} from "../../shared/services/connect-db.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  @Input() jugadorPuntuacioNova : Users | undefined;
  @Output() envia : EventEmitter<Users> = new EventEmitter<Users>();
  constructor(private connectdb : ConnectDBService) {}

  loginForm! : FormGroup;
  users : Users[] = [];
  message! : string;
  ngOnInit() : void {
    this.loginForm = new FormGroup({
      nom : new FormControl('', [Validators.required, Validators.minLength(7)]),
      password : new FormControl('', [Validators.required, Validators.minLength(8)])
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
        console.log(this.users);
        this.message = "L'usuari " + this.loginForm.controls['nom'].value + " s'ha logejat correctament.";
        this.envia.emit(new Users(this.users[0].nom, this.users[0].password, this.users[0].punts));
      }
    })
  }
}
