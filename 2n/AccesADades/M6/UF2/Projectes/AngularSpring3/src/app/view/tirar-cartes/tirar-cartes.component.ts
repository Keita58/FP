import { Component } from '@angular/core';
import {Jugadors} from '../../shared/classes/jugadors';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-tirar-cartes',
  standalone: false,

  templateUrl: './tirar-cartes.component.html',
  styleUrl: './tirar-cartes.component.css'
})
export class TirarCartesComponent {
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
    this.connect.tirarCartes(this.jugador).subscribe(res => {
      this.message = "";
      console.log(res);
      this.message += res.estat;

    });
  }
}
