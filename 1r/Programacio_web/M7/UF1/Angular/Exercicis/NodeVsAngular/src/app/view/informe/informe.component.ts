import {Component, ElementRef, ViewChild} from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {Songs} from "../../shared/classes/songs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import jsPDF from "jspdf";
import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import htmlToPdfmake from "html-to-pdfmake";

@Component({
  selector: 'app-informe',
  templateUrl: './informe.component.html',
  styleUrl: './informe.component.css'
})
export class InformeComponent {

  songs : Songs[] = [];
  songForm! : FormGroup;

  ngOnInit() : void {
    this.songForm = new FormGroup({
      canco : new FormControl('')
    })
  }
  constructor(private connectdb : ConnectDBService) {}

  getSongs() : void {
    this.connectdb.getAllSongs().subscribe(res => {
      if(res.length == 0) {
        this.songs = [];
      }
      else {
        this.songs = res;
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

  filtre() : void {
    this.songs = this.songs.filter((song) => song.Nom.includes(this.songForm.controls['canco'].value));
  }
}
