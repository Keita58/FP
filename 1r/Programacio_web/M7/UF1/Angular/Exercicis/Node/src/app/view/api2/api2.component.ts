import {Component, OnInit} from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {Songs} from "../../shared/classes/songs";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-api2',
  templateUrl: './api2.component.html',
  styleUrl: './api2.component.css'
})
export class Api2Component {
    constructor(private connectdb : ConnectDBService, private songBuilder : FormBuilder) {}
    data!:Songs[];
    songForm : FormGroup = this.songBuilder.group ({
        reproduccions : new FormControl(0),
        recaptacio : new FormControl(0)
    });

    Recuperar() {
        this.connectdb.getSongsRecRep(this.songForm).subscribe(res => {
            if(res.length == 0) {
                this.data = [];
            }
            else {
                this.data = res;
            }
        })
    }
}
