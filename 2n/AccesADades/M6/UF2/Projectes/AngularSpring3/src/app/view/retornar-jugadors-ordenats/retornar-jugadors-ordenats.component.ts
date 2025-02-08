import { Component } from '@angular/core';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-retornar-jugadors-ordenats',
  standalone: false,

  templateUrl: './retornar-jugadors-ordenats.component.html',
  styleUrl: './retornar-jugadors-ordenats.component.css'
})
export class RetornarJugadorsOrdenatsComponent {
  message!:string;
  constructor(private connect: ConnectService){};
  resposta(){
    this.connect.retornarJugadorsOrdenatsPosicio().subscribe(res => {
      this.message = "";
      console.log(res);

      if (res.length > 0){
        for(let i = 0; i < res.length; i++){
          this.message += "<p>Id: " + res[i].idJugador + ", nom: " + res[i].nom
            + ", rol: " + res[i].rolJugador.nomRol + ", posició: " + (res[i].posicio + 1) + "<p/>";
        }

      } else {
        this.message = "<p>No hi ha elements.</p>";
      }
    });
  }
}
