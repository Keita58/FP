import {Component, Input} from '@angular/core';
import {CalcularVocals} from "../../shared/classes/calcular-vocals";
import {Ex3Service} from "../../shared/services/ex3.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-ex3fill',
  templateUrl: './ex3fill.component.html',
  styleUrl: './ex3fill.component.css'
})
export class Ex3fillComponent {

  lletres : CalcularVocals[] = []; //Ho he hagut de posar fora del constructor perquè si no el compilador no reconeixia el component. Per què? Ni idea
  mes1 : boolean = false;
  mes2 : boolean = false;
  mes3 : boolean = false;
  mes4 : boolean = false;
  mes5 : boolean = false;
  constructor(private calcular:Ex3Service) {
  }

  @Input() infoBoto : FormGroup | undefined;
  @Input() formulari : string = '';

  Contar() : void {
    this.mes1 = false;
    this.mes2 = false;
    this.mes3 = false;
    this.mes4 = false;
    this.mes5 = false;
    this.lletres = this.calcular.Calcul(this.formulari);

    let text1 : string = "La vocal " + this.lletres[0].vocal + " està en " + this.lletres[0].comptador + ". Aquestes són: [" + this.lletres[0].paraules + "]";
    let text2 : string = "La vocal " + this.lletres[1].vocal + " està en " + this.lletres[1].comptador + ". Aquestes són: [" + this.lletres[1].paraules + "]";
    let text3 : string = "La vocal " + this.lletres[2].vocal + " està en " + this.lletres[2].comptador + ". Aquestes són: [" + this.lletres[2].paraules + "]";
    let text4 : string = "La vocal " + this.lletres[3].vocal + " està en " + this.lletres[3].comptador + ". Aquestes són: [" + this.lletres[3].paraules + "]";
    let text5 : string = "La vocal " + this.lletres[4].vocal + " està en " + this.lletres[4].comptador + ". Aquestes són: [" + this.lletres[4].paraules + "]";

    if(this.lletres[0].paraules.length > 1)
      this.mes1 = true;
    if(this.lletres[1].paraules.length > 1)
      this.mes2 = true;
    if(this.lletres[2].paraules.length > 1)
      this.mes3 = true;
    if(this.lletres[3].paraules.length > 1)
      this.mes4 = true;
    if(this.lletres[4].paraules.length > 1)
      this.mes5 = true;

    // @ts-ignore
    document.getElementById("1").innerText = text1;
    // @ts-ignore
    document.getElementById("2").innerText = text2;
    // @ts-ignore
    document.getElementById("3").innerText = text3;
    // @ts-ignore
    document.getElementById("4").innerText = text4;
    // @ts-ignore
    document.getElementById("5").innerText = text5;
  }
}
