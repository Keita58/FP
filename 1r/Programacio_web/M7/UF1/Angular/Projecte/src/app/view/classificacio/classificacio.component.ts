import { Component, ElementRef, ViewChild } from '@angular/core';
import jsPDF from "jspdf";
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import htmlToPdfmake from "html-to-pdfmake";
import { Users } from '../../shared/classes/users';
import { ConnectDBService } from '../../shared/services/connect-db.service';

@Component({
  selector: 'app-classificacio',
  templateUrl: './classificacio.component.html',
  styleUrl: './classificacio.component.css'
})
export class ClassificacioComponent {
  sortedArray: any;

  constructor(private connectdb : ConnectDBService) {}
  users : Users[] = [];

  getUsers() : void {
    this.connectdb.getAllUsers().subscribe(res => {
      if(res.length == 0) {
        this.users = [];
      }
      else {
        this.users = res;
        this.users = this.users.sort((a, b) => b.punts - a.punts); //* Ordenem els jugadors per les seves puntuacions
        console.log(this.users);
      }
    })
  }

  @ViewChild('pdfTable') pdfTable !: ElementRef;
  getPDF() {
    const doc = new jsPDF();
    const pdfTable = this.pdfTable.nativeElement;
    var html = htmlToPdfmake(pdfTable.innerHTML);
    const documentDefinition = {content: html};
    pdfMake.createPdf(documentDefinition).open();
  }
}
