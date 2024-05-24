import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-comptar',
  templateUrl: './comptar.component.html',
  styleUrl: './comptar.component.css'
})
export class ComptarComponent {
  res! : string;
  vocalsa! : number;
  vocalse! : number;
  vocalsi! : number;
  vocalso! : number;
  vocalsu! : number;

  constructor(private formBuilder : FormBuilder) {}

  formulari : FormGroup = this.formBuilder.group({
    frase: ['', [Validators.minLength(1), Validators.required]]
  });

  Calcular() : void {
    let a : RegExpMatchArray = (this.formulari.controls['frase'].value as string).match(/[a]/gi) as RegExpMatchArray; //Regex per la vocal a, la g es per a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    this.vocalsa = a === null ? 0 : a.length;
    let e : RegExpMatchArray = (this.formulari.controls['frase'].value as string).match(/[e]/gi) as RegExpMatchArray; //Regex per la vocal e, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    this.vocalse = e === null ? 0 : e.length;
    let i : RegExpMatchArray = (this.formulari.controls['frase'].value as string).match(/[i]/gi) as RegExpMatchArray; //Regex per la vocal i, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    this.vocalsi = i === null ? 0 : i.length;
    let o : RegExpMatchArray = (this.formulari.controls['frase'].value as string).match(/[o]/gi) as RegExpMatchArray; //Regex per la vocal o, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    this.vocalso = o === null ? 0 : o.length;
    let u : RegExpMatchArray = (this.formulari.controls['frase'].value as string).match(/[u]/gi) as RegExpMatchArray; //Regex per la vocal u, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    this.vocalsu = u === null ? 0 : u.length;

    this.res = "En el text anterior hi havia " + this.vocalsa + " 'a', " + this.vocalse + " 'e', " + this.vocalsi + " 'i', " + this.vocalso + " 'o' i " + this.vocalsu + " 'u'.";
  }
}
