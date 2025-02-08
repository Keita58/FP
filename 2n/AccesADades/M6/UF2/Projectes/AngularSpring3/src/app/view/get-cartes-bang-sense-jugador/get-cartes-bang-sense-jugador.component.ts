import { Component } from '@angular/core';
import {ConnectService} from '../../shared/services/connect.service';

@Component({
  selector: 'app-get-cartes-bang-sense-jugador',
  standalone: false,

  templateUrl: './get-cartes-bang-sense-jugador.component.html',
  styleUrl: './get-cartes-bang-sense-jugador.component.css'
})
export class GetCartesBangSenseJugadorComponent {
  message!:string;
  constructor(private connect: ConnectService){};
  resposta(){
    this.connect.getCartesBangSenseJugador().subscribe(res => {
      this.message = "";
      console.log(res);

      if (res.length > 0){
        for(let i = 0; i < res.length; i++){
          this.message += "<p>Id: " + res[i].idCarta
            + ", Tipus Carta: " + res[i].cartaTipusCarta.nom + "<p/>";

        }

      } else {
        this.message = "<p>No hi ha elements.</p>";
      }
    });
  }
}
