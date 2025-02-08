import { Component } from '@angular/core';
import {Jugadors} from '../../shared/classes/jugadors';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-agafar-cartes',
  standalone: false,

  templateUrl: './agafar-cartes.component.html',
  styleUrl: './agafar-cartes.component.css'
})
export class AgafarCartesComponent {
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
    this.connect.agafarCartes(this.jugador).subscribe(res => {
      this.message = "";
      console.log(res);
      this.message += res.estat;

    });
  }
}
