import {Component, Injectable} from '@angular/core';
import {Volum} from "../../shared/classes/volum";
import {Ex2Service} from "../../shared/services/ex2.service";

@Component({
  selector: 'app-ex2',
  templateUrl: './ex2.component.html',
  styleUrl: './ex2.component.css',
  providers: []
})

@Injectable()
export class Ex2Component {

  constructor(private func:Ex2Service) {
  }

  resposta:string = '';
  gran:boolean = false;
  Dades(info:Volum) {
    var area : number = this.func.VolumCilindre(info.radi, info.altura, info.unitats);
    var area2 : number = this.func.VolumCilindre2(info.radi);

    if(area >= area2) {
      this.resposta = "El volum és " + area + " " + info.unitats + " al cub. El nombre aleatori és " + area2 + ". El nombre aleatori és més petit o igual."
      this.gran = true;
    }
    else {
      this.resposta = "El volum és " + area + " " + info.unitats + " al cub. El nombre aleatori és " + area2 + ". El nombre aleatori és més gran."
      this.gran = false;
    }
  }

}
