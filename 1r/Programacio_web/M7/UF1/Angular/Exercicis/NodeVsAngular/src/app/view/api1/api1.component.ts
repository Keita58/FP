import { Component } from '@angular/core';
import {Users} from "../../shared/classes/users";
import {ConnectDBService} from "../../shared/services/connect-db.service";

@Component({
  selector: 'app-api1',
  templateUrl: './api1.component.html',
  styleUrl: './api1.component.css'
})
export class Api1Component {
  constructor(private connectdb : ConnectDBService) {}
  data! : Users[];
  //Els atributs de "Users" han d'estar en minúscula perquè en la base de dades estan en minúscula,
  // han de ser iguals tots dos atributs!!

  getUsers() : void {
    this.connectdb.getAllUsers().subscribe(res => {
      console.log(res);
      if(res.length == 0) {
        this.data = [];
      }
      else {
        this.data = res;
      }
    })
  }
}
