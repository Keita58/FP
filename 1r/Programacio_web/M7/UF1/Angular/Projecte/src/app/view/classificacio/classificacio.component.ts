import { Component, ElementRef, ViewChild } from '@angular/core';
import jsPDF from "jspdf";
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import htmlToPdfmake from "html-to-pdfmake";
import { Users } from '../../shared/classes/users';
import { ConnectDBService } from '../../shared/services/connect-db.service';

/**
 * Component de la classificació dels jugadors
 */
@Component({
  selector: 'app-classificacio',
  templateUrl: './classificacio.component.html',
  styleUrl: './classificacio.component.css'
})


export class ClassificacioComponent {
  /**
   * @ignore
   */
  sortedArray: any;

  /**
   * @ignore
   */
  constructor(private connectdb : ConnectDBService) {}

  /**
   * @ignore
   */
  users : Users[] = [];

  /**
   * Rep tots els usuaris de la base de dades a partir de la funció del servei {@link ConnectDBService}
   * i els ordena de major puntuació a menor.
   * Si la base de dades no té usuaris passa a la variable users un array buit.
   * @returns void
   */
  getUsers() : void {
    this.connectdb.getAllUsers().subscribe(res => {
      this.users = [];
      if(res.length == 0) {
        this.users = [];
      }
      else {
        this.users = res;
        this.users = this.users.sort((a, b) => b.punts - a.punts); //* Ordenem els jugadors per les seves puntuacions
        //console.log(this.users);
      }
    })
  }

  /**
   * Passem tota la informació que hi ha a la taula de classificació a un pdf que se'ns descarregarà automàticament.
   * Primer reconeix les dades que hi ha la taula amb el pdfTable, després passem tota aquesta a la variable html, que d'aquesta
   * ho passem a documentDefinition només amb la informació i d'aquesta creem el pdf amb la funció pròpia de {@link pdfMake} createPdf.
   * @return void
   */
  @ViewChild('pdfTable') pdfTable !: ElementRef;
  getPDF() {
    const doc = new jsPDF();
    const pdfTable = this.pdfTable.nativeElement;
    var html = htmlToPdfmake(pdfTable.innerHTML);
    const documentDefinition = {content: html};
    pdfMake.createPdf(documentDefinition).open();
  }
}
