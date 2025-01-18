import { Component } from '@angular/core';
import {Jugadors} from '../../shared/classes/jugadors';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-final-partida',
  standalone: false,

  templateUrl: './final-partida.component.html',
  styleUrl: './final-partida.component.css'
})
export class FinalPartidaComponent {
  message!: string;
  constructor(private connect: ConnectService, private jService : JugadorsService){};

  resposta(){
    this.connect.finalPartida().subscribe(res => {
      this.message = "";
      console.log(res);
      this.message += res.estat;

    });
  }
}
