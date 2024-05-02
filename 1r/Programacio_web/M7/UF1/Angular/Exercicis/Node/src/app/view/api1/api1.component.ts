import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {Songs} from "../../shared/classes/songs";

@Component({
  selector: 'app-api1',
  templateUrl: './api1.component.html',
  styleUrl: './api1.component.css'
})
export class Api1Component {
    constructor(private connectdb : ConnectDBService) {}
    data!: Songs[];

    getSongs() : void {
        this.connectdb.getAllSongs().subscribe(res => {
            if(res.length == 0) {
                this.data = [];
            }
            else {
                this.data = res;
            }
        })
    }
}
