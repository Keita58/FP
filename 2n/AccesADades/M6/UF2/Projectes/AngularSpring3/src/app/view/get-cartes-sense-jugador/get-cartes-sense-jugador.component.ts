import { Component } from '@angular/core';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-get-cartes-sense-jugador',
  standalone: false,

  templateUrl: './get-cartes-sense-jugador.component.html',
  styleUrl: './get-cartes-sense-jugador.component.css'
})
export class GetCartesSenseJugadorComponent {
  message!:string;
  constructor(private connect: ConnectService){};
  resposta(){
    this.connect.getCartesSenseJugador().subscribe(res => {
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
