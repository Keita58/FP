import { Component } from '@angular/core';
import {ConnectService} from '../../shared/services/connect.service';
import {JugadorsService} from '../../shared/services/jugadors-service.service';

@Component({
  selector: 'app-get-partides-is-finalitzada',
  standalone: false,

  templateUrl: './get-partides-is-finalitzada.component.html',
  styleUrl: './get-partides-is-finalitzada.component.css'
})
export class GetPartidesIsFinalitzadaComponent {
  message!:string;
  constructor(private connect: ConnectService){};
  resposta(){
    this.connect.getPartidesNoFinalitzades().subscribe(res => {
      this.message = "";
      console.log(res);

      if (res.length > 0){
        for(let i = 0; i < res.length; i++){
          this.message += "<p>Id: " + res[i].idPartida;
        }

      } else {
        this.message = "<p>No hi ha elements.</p>";
      }
    });
  }
}
