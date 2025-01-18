import { Component } from '@angular/core';
import {Jugadors} from '../../shared/classes/jugadors';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-reassignar-distancies',
  standalone: false,

  templateUrl: './reassignar-distancies.component.html',
  styleUrl: './reassignar-distancies.component.css'
})
export class ReassignarDistanciesComponent {
  message!: string;

  constructor(private connect: ConnectService){};

  resposta(){
    this.connect.reassignarDistancies().subscribe(res => {
      this.message = "";
      console.log(res);

      if (res.length > 0){
        for(let i = 0; i < res.length; i++){
          this.message += "<p>Id: " + res[i].idRival.idJugador.idJugador + ", nom: "
            + res[i].idRival.idJugador.nom + ", IdRival: " + res[i].idRival.idRival.idJugador + ", nom: "
          + res[i].idRival.idRival.nom + ", Distància: " + res[i].distanciaRival + "<p/>";
        }

      } else {
        this.message = "<p>No hi ha elements.</p>";
      }
    });
  }
}
