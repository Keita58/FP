import { Component, HostListener } from '@angular/core';
import { FormGroup } from '@angular/forms';
import {Users} from "../../shared/classes/users";

@Component({
  selector: 'app-joc',
  templateUrl: './joc.component.html',
  styleUrl: './joc.component.css'
})
export class JocComponent {

  constructor(public usuari : Users) {
  }

  grid : string[][] = [
    ['wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'eloi', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'dot-unhit', 'wall'],
    ['wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall', 'wall']
  ];

  base : string[][] = JSON.parse(JSON.stringify(this.grid)); //* Això és per a reiniciar el joc (clonem la matriu original)
  //* base és la matriu que modificarem
  //* grid és la matriu original que utilitzarem per reiniciar el joc

  i : number = 1; //* Aquesta i és la posició en les y de l'Eloi
  punts : number = 0; //* Puntuació del jugador
  perduts : number = 0;

  @HostListener('window:keydown', ['$event'])
  handleKeyDown(event: KeyboardEvent) {
    switch (event.code) {
      case 'ArrowLeft':
        if(this.i > 1 && this.base[9][this.i-1] == "dot-unhit") {
          //* Amb això el que fem és moure la posició a la que anirà l'Eloi per la que existeix, que en aquest cas és un dot-unhit
          [this.base[9][this.i], this.base[9][this.i-1]] = [this.base[9][this.i-1], this.base[9][this.i]];
          this.i--;
        }
        else if(this.base[9][this.i-1] == "work") {
          //TODO Afegir puntuació al jugador, aquí agafa un treball
          this.punts++;

          //* Aquí fem el mateix que en l'anterior if però eliminant el work i transformant-lo en un dot-unhit
          this.base[9][this.i-1] = "dot-unhit";
          [this.base[9][this.i], this.base[9][this.i-1]] = [this.base[9][this.i-1], this.base[9][this.i]];
          this.i--;
        }
        break;

      case 'ArrowRight':
        if(this.i < 8 && this.base[9][this.i+1] == "dot-unhit") {
          //* Amb això el que fem és moure la posició a la que anirà l'Eloi per la que existeix, que en aquest cas és un dot-unhit
          [this.base[9][this.i], this.base[9][this.i+1]] = [this.base[9][this.i+1], this.base[9][this.i]];
          this.i++;
        }
        else if(this.base[9][this.i+1] == "work") {
          //TODO Afegir puntuació al jugador, aquí agafa un treball
          this.punts++;

          //* Aquí fem el mateix que en l'anterior if però eliminant el work i transformant-lo en un dot-unhit
          this.base[9][this.i+1] = "dot-unhit";
          [this.base[9][this.i], this.base[9][this.i+1]] = [this.base[9][this.i+1], this.base[9][this.i]];
          this.i++;
        }
        break;
      default:
        break;
    }
  }
  movimentid : any;
  comptar : any;
  temps : number = 0;
  spawnEnemics : number = 1000;

  inici() : void {
    console.log(this.spawnEnemics);
    if(this.temps == 20){
      this.spawnEnemics -= 125;
      this.temps = 0;
      clearInterval(this.movimentid);
      this.movimentid = setInterval((): void => {
        this.inici()
      }, this.spawnEnemics);
    }
    if(this.perduts == 10) {
      clearInterval(this.movimentid);
      if(this.usuari.punts <= this.punts)
        this.usuari.punts = this.punts;
    }
    else {
      for(let j : number = 9; j > 0; j--) {
        for(let k : number = 8; k > 0; k--) {
          if(this.base[j][k] == "work" && this.base[j+1][k] == "dot-unhit") {
            [this.base[j][k], this.base[j+1][k]] = [this.base[j+1][k], this.base[j][k]];
          }
          else if (this.base[j][k] == "work" && this.base[j+1][k] == "eloi") {
            this.base[j][k] = "dot-unhit";
            this.punts++;
          }
          else if (this.base[j][k] == "work" && this.base[j+1][k] == "wall") {
            this.base[j][k] = "dot-unhit";
            this.perduts++;
          }
        }
      }
      let surt : number = Math.round(Math.random());
      let r : number = Math.floor(Math.random() * (9 - 1) + 1);
      if (surt == 1) {
        this.base[1][r] = "work";
      }
    }
  }

  spawner(reinici : boolean) : void {
    this.punts = 0;
    this.perduts = 0;
    this.movimentid = setInterval((): void => {
      this.inici()
    }, 1000);
    this.comptar = setInterval((): void => {
      this.temps++;
    }, 1000);
    if(reinici) {
      this.base = JSON.parse(JSON.stringify(this.grid)); //* Tornem a clonar la matriu per a reiniciar el tauler
      clearInterval(this.movimentid);
    }
  }
}
