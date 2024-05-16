import { Component, HostListener } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-joc',
  templateUrl: './joc.component.html',
  styleUrl: './joc.component.css'
})
export class JocComponent {

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

  i : number = 1; //* Aquesta i és la posició en les y de l'Eloi
  punts : number = 0; //* Puntuació del jugador

  @HostListener('window:keydown', ['$event'])
  handleKeyDown(event: KeyboardEvent) {
    switch (event.code) {
      case 'ArrowLeft':
        if(this.i > 1 && this.grid[9][this.i-1] == "dot-unhit") {

          //* Amb això el que fem és moure la posició a la que anirà l'Eloi per la que existeix, que en aquest cas és un dot-unhit
          [this.grid[9][this.i], this.grid[9][this.i-1]] = [this.grid[9][this.i-1], this.grid[9][this.i]];
          this.i--;
        }
        else if(this.grid[9][this.i-1] == "work") {
          //TODO Afegir puntuació al jugador, aquí agafa un treball
          this.punts++;

          //* Aquí fem el mateix que en l'anterior if però eliminant el work i transformant-lo en un dot-unhit
          this.grid[9][this.i-1] = "dot-unhit";
          [this.grid[9][this.i], this.grid[9][this.i-1]] = [this.grid[9][this.i-1], this.grid[9][this.i]];
          this.i--;
        }
        break;

      case 'ArrowRight':
        if(this.i < 8 && this.grid[9][this.i+1] == "dot-unhit") {

          //* Amb això el que fem és moure la posició a la que anirà l'Eloi per la que existeix, que en aquest cas és un dot-unhit
          [this.grid[9][this.i], this.grid[9][this.i+1]] = [this.grid[9][this.i+1], this.grid[9][this.i]];
          this.i++;
        }
        else if(this.grid[9][this.i+1] == "work") {
          //TODO Afegir puntuació al jugador, aquí agafa un treball
          this.punts++;

          //* Aquí fem el mateix que en l'anterior if però eliminant el work i transformant-lo en un dot-unhit
          this.grid[9][this.i+1] = "dot-unhit";
          [this.grid[9][this.i], this.grid[9][this.i+1]] = [this.grid[9][this.i+1], this.grid[9][this.i]];
          this.i++;
        }
        break;
      default:
        break;
    }
  }

  sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

  comencar() : void {
    setInterval(this.inici, 3000, this.grid);
  }

  moviment(i : number, pos : number, grid : string[][]) : void {
    while(true) {
      [grid[i][pos], grid[i+1][pos]] = [grid[i+1][pos], grid[i][pos]];
      i++;
      this.sleep(3000);
    }
  }

  inici(grid : string[][]) : void {
    let r = Math.floor(Math.random() * (9 - 1) + 1);
    console.log(grid);
    grid[1][r] = "work";
    let i : number = 2;
    this.moviment(i, r, grid);
  }
}
