import { Injectable } from '@angular/core';
import {CalcularVocals} from "../classes/calcular-vocals";

@Injectable({
  providedIn: 'root'
})
export class Ex3Service {

  constructor() {}

  Calcul(formulari: string) {

    var vocalsa : number = 0;
    var paraulesA : string[] = [];

    var vocalse : number = 0;
    var paraulesE : string[] = [];

    var vocalsi : number = 0;
    var paraulesI : string[] = [];

    var vocalso : number = 0;
    var paraulesO : string[] = [];

    var vocalsu : number = 0;
    var paraulesU : string[] = [];

    var paraules : string[] = formulari.split(' ');

    paraules.forEach(function(paraula : string) : void {
      if(paraula.match(/a/gi)) {
        paraulesA.push(paraula);
        vocalsa++;
      }
      if(paraula.match(/e/gi)) {
        paraulesE.push(paraula);
        vocalse++;
      }
      if(paraula.match(/i/gi)) {
        paraulesI.push(paraula);
        vocalsi++;
      }
      if(paraula.match(/o/gi)) {
        paraulesO.push(paraula);
        vocalso++;
      }
      if(paraula.match(/u/gi)) {
        paraulesU.push(paraula);
        vocalsu++;
      }
    })

    var a2 : CalcularVocals = new CalcularVocals("a", vocalsa, paraulesA);
    var e2 : CalcularVocals = new CalcularVocals("e", vocalse, paraulesE);
    var i2 : CalcularVocals = new CalcularVocals("i", vocalsi, paraulesI);
    var o2 : CalcularVocals = new CalcularVocals("o", vocalso, paraulesO);
    var u2 : CalcularVocals = new CalcularVocals("u", vocalsu, paraulesU);

    var result : CalcularVocals[] = [];
    result.push(a2);
    result.push(e2);
    result.push(i2);
    result.push(o2);
    result.push(u2);

    return result;
  }
}
