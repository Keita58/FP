import { Component } from '@angular/core';
import {Jugadors} from '../../shared/classes/jugadors';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-get-jugadors-amb-personatges-vida-altres',
  standalone: false,

  templateUrl: './get-jugadors-amb-personatges-vida-altres.component.html',
  styleUrl: './get-jugadors-amb-personatges-vida-altres.component.css'
})
export class GetJugadorsAmbPersonatgesVidaAltresComponent {
  message!: string;
  jugador : number = 0;
  jugadors !: Jugadors;
  constructor(private connect: ConnectService, private jService : JugadorsService){};
  ngOnInit() {

    this.jService.data$.subscribe((data) => {
      this.jugadors = data;
      let counter:number = 0;
      while (this.jugador === 0 && counter < 20) {
        this.jugador = this.jugadors.idJugador[Math.floor(Math.random() * 8)];
        counter ++;
      }
    });

  }
  resposta(){
    this.connect.getJugadorsAmbPersonatgesVidaAltres(this.jugador).subscribe(res => {
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
