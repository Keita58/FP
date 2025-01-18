import { Component } from '@angular/core';
import {ConnectService} from '../../shared/services/connect.service';
import {FormControl, FormControlName} from '@angular/forms';

@Component({
  selector: 'app-get-jugadors-rols-vida',
  standalone: false,

  templateUrl: './get-jugadors-rols-vida.component.html',
  styleUrl: './get-jugadors-rols-vida.component.css'
})
export class GetJugadorsRolsVidaComponent {
  message!:string;
  rols = ['XERIF', 'MALFACTOR', 'RENEGAT','AJUDANT'];
  selectedRol: string = '';
  rolEscollit:FormControl = new FormControl('XERIF');
  ngOnInit(): void {

    this.rolEscollit.valueChanges.subscribe(newValue => {
      this.rolEscollit.setValue(newValue) ;
    });
  }
  constructor(private connect: ConnectService){};
  resposta(){
    this.connect.getJugadorsRolsVida(this.rolEscollit.value ).subscribe(res => {
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
