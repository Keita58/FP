import { Component, HostListener } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-joc',
  templateUrl: './joc.component.html',
  styleUrl: './joc.component.css'
})
export class JocComponent {

  grid:string[][] = [
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

  i : number = 1; //* Aquesta i és la posició en les y de l'Eloi 
  punts : number = 0; //* Puntuació del jugador

  @HostListener('window:keydown', ['$event'])
  handleKeyDown(event: KeyboardEvent) {
    switch (event.code) {
      case 'ArrowLeft':
        console.log("Esquerra");
        if(this.i > 1 && this.grid[9][this.i-1] == "dot-unhit") {

          //*Amb això el que fem és moure la posició a la que anirà l'Eloi per la que existeix, que en aquest cas és un dot-unhit
          [this.grid[9][this.i], this.grid[9][this.i-1]] = [this.grid[9][this.i-1], this.grid[9][this.i]];
          this.i--;
        }
        else if(this.grid[9][this.i-1] == "work") {
          //TODO Afegir puntuació al jugador, aquí agafa un treball
          this.punts++;

          //*Aquí fem el mateix que en l'anterior if però eliminant el work i transformant-lo en un dot-unhit
          this.grid[9][this.i-1] = "dot-unhit";
          [this.grid[9][this.i], this.grid[9][this.i-1]] = [this.grid[9][this.i-1], this.grid[9][this.i]];
          this.i--;
        }
        break;

      case 'ArrowRight':
        console.log("Dreta");
        if(this.i < 8 && this.grid[9][this.i+1] == "dot-unhit") {

          //*Amb això el que fem és moure la posició a la que anirà l'Eloi per la que existeix, que en aquest cas és un dot-unhit
          [this.grid[9][this.i], this.grid[9][this.i+1]] = [this.grid[9][this.i+1], this.grid[9][this.i]];
          this.i++;
        }
        else if(this.grid[9][this.i+1] == "work") {
          //TODO Afegir puntuació al jugador, aquí agafa un treball
          this.punts++;

          //*Aquí fem el mateix que en l'anterior if però eliminant el work i transformant-lo en un dot-unhit
          this.grid[9][this.i+1] = "dot-unhit";
          [this.grid[9][this.i], this.grid[9][this.i+1]] = [this.grid[9][this.i+1], this.grid[9][this.i]];
          this.i++;
        }
        break;
      default:
        break;
    }
    
    console.log(this.grid[9]);
    console.log(this.i);
  }

  comencar() : void {
    let interval = setInterval(this.treballs, 3000);
  }

  treballs(): void {
    throw new Error('Function not implemented.');
  }
}
