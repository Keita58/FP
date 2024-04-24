import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Ex2Service {

  constructor() { }

  VolumCilindre(radi: number, altura: number, unitats: string):number {

    return Math.PI*(Math.pow(radi, 2))*altura;
  }

  VolumCilindre2(radi: number) {
    var altura : number = Math.floor(Math.random() * (1000 - 1) + 1);

    return Math.PI*(Math.pow(radi, 2))*altura;
  }
}
