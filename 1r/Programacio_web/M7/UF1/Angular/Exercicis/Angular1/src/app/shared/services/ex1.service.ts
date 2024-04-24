import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Ex1Service {

  constructor() { }

  Daus():number[] {
    let daus:number[] = new Array(3);
    daus[0] = Math.floor(Math.random() * (7 - 1) + 1);
    daus[1] = Math.floor(Math.random() * (7 - 1) + 1);
    daus[2] = Math.floor(Math.random() * (7 - 1) + 1);

    return daus;
  }
}
