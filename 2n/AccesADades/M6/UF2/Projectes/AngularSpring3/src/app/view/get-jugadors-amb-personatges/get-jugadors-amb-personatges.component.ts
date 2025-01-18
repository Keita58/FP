import { Component } from '@angular/core';
import {ConnectService} from '../../shared/services/connect.service';
import {Jugadors} from '../../shared/classes/jugadors';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-get-jugadors-amb-personatges',
  standalone: false,

  templateUrl: './get-jugadors-amb-personatges.component.html',
  styleUrl: './get-jugadors-amb-personatges.component.css'
})
export class GetJugadorsAmbPersonatgesComponent {
  message!:string;
  jugadors !: Jugadors;
  ngOnInit() {
    this.jService.data$.subscribe((data) => {
      this.jugadors = data;
    });
  }
  constructor(private connect: ConnectService, private jService : JugadorsService){};
  resposta(){
    this.connect.getJugadorsAmbPersonatgesVida().subscribe(res => {
      this.message = "";
      console.log(res);

      if (res.length > 0){
        for(let i = 0; i < res.length; i++){
          this.message += "<p>Id: " + res[i].idJugador + ", nom: " + res[i].nom
            + ", rol: " + res[i].rolJugador.nomRol + "<p/>";
          this.jugadors.idJugador[i] = res[i].idJugador;
        }
        this.jService.updateData(this.jugadors);
      } else {
        this.message = "<p>No hi ha elements.</p>";
      }
    });
  }
}
