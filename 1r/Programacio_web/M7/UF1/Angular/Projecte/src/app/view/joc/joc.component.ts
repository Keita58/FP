import { Component, HostListener } from '@angular/core';
import {Users} from "../../shared/classes/users";
import { LoginServiceService } from '../../shared/services/login-service.service';
import { ConnectDBService } from '../../shared/services/connect-db.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-joc',
  templateUrl: './joc.component.html',
  styleUrl: './joc.component.css'
})
export class JocComponent {
  /**
   * Constructor base de la classe JocComponent, on inicialitzem dues variables, una per a la connexió amb el servidor i l'altre per a 
   * poder actualitzar la puntuació del jugador si supera el seu rècord.
   */
  constructor(private login : LoginServiceService, private connectdb : ConnectDBService) {}

  /**
   * @ignore
   */
  usuari = this.login.userLogin.getValue();

  /**
   * @ignore
   */
  loginForm! : FormGroup;

  /**
   * Estat inicial del grid del joc. És una matriu 10x11 on totes les caselles exteriors són les parets, la casella [1][9] és on comença el jugador i la resta
   * són caselles "buides" (segueixen existint però conten com a buides per al joc).
   * @type {string}
   */
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

  /**
   * La matriu del joc (modificable).
   * @type {string}
   */
  base : string[][] = JSON.parse(JSON.stringify(this.grid)); 
  //* El que estem fet aquí es fer una còpia exacte de la variable grid, que conté l'estat inicial del joc. I això ho fem per a quan haguem de reiniciar el
  //* tauler tenir una variable sense modificar i que puguem copiar sense problemes (la variable grid que no utilitzarem per a cap cosa que no sigui per a
  //* copiar-la a la variable base).

  /**
   * Posició en les y del jugador.
   * @type {number}
   */
  i : number = 1;

  /**
   * Variable de la puntuació del jugador.
   * @type {number}
   */
  punts : number = 0;

  /**
   * Treballs perduts (que han tocat el terra) pel jugador.
   * @type {number}
   */
  perduts : number = 0;

  /**
   * Aquesta funció sencera és la que s'encarrega del moviment del jugador en el joc.
   * El que realitza és analitzar quina tecla s'està utilitzant cada vegada i si és qualsevol de les dues fletxes (dreta o esquerra) realitza el moviment.
   * 
   * Els moviments tant per la dreta com per a l'esquerra són extremadament similars; tenim primer un switch case on es comprova si el jugador es vol moure 
   * cap a la dreta o cap a la l'esquerra. Si es vol moure cap a l'esquerra (per exemple) després comprovem amb un if else què hi té al costat, si una casella 
   * buida (amb el nom de 'dot-unhit') o un treball (amb el nom de 'work').
   * Si té una casella buida el que fa és canviar les posicions amb la casella buida i modifica la variable 'i', que és la posició en les y del jugador.
   * Si té un treball fa una cosa semblant a l'anterior però modificant la casella de 'work' per una de 'dot-unhit' abans de canviar les posicions i suma 
   * un punt al marcador. 
   *  
   * @param {KeyboardEvent} event  Variable que ens retorna quina tecla del teclat estem polsant
   */
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
          //* Afegim un punt al jugador si té el treball als seus costats i l'agafa
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
          //* Afegim un punt al jugador si té el treball als seus costats i l'agafa
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

  /**
   * @ignore
   */
  movimentid : any;

  /**
   * @ignore
   */
  comptar : any;

  /**
   * @ignore
   */
  temps : number = 1; //* Per calcular el temps que passa abans no augmentar la velocitat dels treballs

  /**
   * Variable de la velocitat a la que cauen els treballs (en ms).
   * @type {number}
   */
  spawnEnemics : number = 1000;

  /**
   * @ignore
   */
  loguejat : string = '';

  /**
   * Temps total del joc actual.
   * @type {number}
   */
  segons : number = 0;

  /**
   * S'inicialitza al principi de tot d'inicialitzar el projecte.
   * Comprova que la variable usuari tingui informació (que l'usuari estigui loguejat a la pàgina).
   * Si no té informació vol dir que no s'ha loguejat i modifica el text d'avís per notificar l'usuari que per jugar s'ha de loguejar.
   */
  ngOnInit() {
    if(this.usuari == 'logout')
      this.loguejat = "T'has de loguejar/registrar abans de jugar!";
  }

  /**
   * Funció del moviment i spawn dels treballs.
   * 
   * Primer es comprova si han passat 20s per a reduir el temps d'spawn dels enemics i si aquest temps d'spawn no és menor a 250ms. 
   * Si han passat els 20s i el temps d'spawn és major a 250ms aquest es redueix en 125ms, es reinicialitza el temporitzador a 0, s'esborra l'interval existent de l'spawn dels enemics
   * i es crea un de nou amb l'interval reduit.
   * 
   * Després comprova si ja s'han perdut 10 treballs. Si és el cas, esborra tots els intervals existents i si la puntuació aconseguida és major a la que 
   * té el jugador actualment envia aquesta puntuació a la funció updateUser de {@link ConnectDBService} que l'actualitzarà a la base de dades.
   * Si no és el cas i encara no ha perdut els 10 treballs, els mou tots una casella avall en un bucle for començant des de la part de baix de la matriu
   * i després en crea un a la part de dalt del tauler amb una probabilitat del 50% en una posició aleatòria (entre al columna 1 i la 8).
   * 
   * @returns void
   */
  inici() : void {
    console.log(this.spawnEnemics);
    if(this.temps == 20 && this.spawnEnemics > 250){
      this.spawnEnemics -= 125;
      this.temps = 0;
      clearInterval(this.movimentid);
      this.movimentid = setInterval((): void => {
        this.inici()
      }, this.spawnEnemics);
    }
    if(this.perduts == 10) {
      clearInterval(this.movimentid);
      clearInterval(this.comptar);
      console.log(this.usuari);

      if(this.usuari[0].punts <= this.punts) {
        this.usuari[0].punts = this.punts;
        console.log("Punts: " + this.usuari[0].punts);
        this.loginForm = new FormGroup({
          nom : new FormControl(this.usuari[0].nom),
          punts : new FormControl(this.usuari[0].punts)
        });

        this.connectdb.updateUser(this.loginForm).subscribe(res => {
          console.log("Update fet");
        });
      }
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

  /**
   * S'inicialitza quan el jugador clica a començar en l'html o a reiniciar.
   * Si aquest no té sessió no fa res. Si en té inicia totes les variables als seus estats inicials; la puntuació a 0, els treballs a 0 i l'spawn de 
   * treballs a 1000ms.
   * Si ha fet click a 'Començar!' (és a dir, no ha jugat encara) es creen dos intervals; el primer per a l'spawn i creació dels treballs i el segon per a 
   * comptar els segons que passen, tant per al temporitzador que es mostra com per al de reduir el temps entre spawns dels treballs.
   * Si ha fet click a 'Reinici' reiniciem el tauler (la variable base d'aquest component), esborrem i tornem a crear els intervals anteriors i canviem 
   * la posició del jugador actual per la que està en la matriu original per a que no hi hagi problemes amb el moviment.
   * 
   * @param reinici  Si el jugador fa click a jugar rep un false, si fa click a reinici rep un true
   * @returns void
   */
  spawner(reinici : boolean) : void {
    if(this.usuari != 'logout') {
      this.loguejat = '';
      this.punts = 0;
      this.perduts = 0;
      this.spawnEnemics = 1000;
      if(!reinici) {
        this.movimentid = setInterval((): void => {
          this.inici()
        }, 1000);
        this.comptar = setInterval((): void => {
          this.temps++;
          this.segons++;
        }, 1000);
      }
      else {
        this.segons = 1;
        this.base = JSON.parse(JSON.stringify(this.grid)); //* Tornem a clonar la matriu per a reiniciar el tauler
        clearInterval(this.movimentid);
        clearInterval(this.comptar);
        this.movimentid = setInterval((): void => {
          this.inici()
        }, 1000);
        this.comptar = setInterval((): void => {
          this.temps++;
          this.segons++;
        }, 1000);
        [this.base[9][this.i], this.base[9][1]] = [this.base[9][1], this.base[9][this.i]]; //* Canviem la posició actual de l'Eloi per la posició en la que està originalment per a que es pugui moure quan reiniciem
      }
    }
  }
}
