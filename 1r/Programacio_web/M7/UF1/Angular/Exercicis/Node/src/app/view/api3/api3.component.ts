import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-api3',
  templateUrl: './api3.component.html',
  styleUrl: './api3.component.css'
})
export class Api3Component {
    constructor(private connectdb : ConnectDBService, private form : FormBuilder) {}

    resultat! : string;

    songForm : FormGroup = this.form.group({
        nom : new FormControl(''),
        reproduccions : new FormControl(0),
        recaptacio : new FormControl(0)
    })

    Guardar() {
        this.connectdb.setSong(this.songForm).subscribe(res => {
            this.resultat = res;
        })
    }
}
