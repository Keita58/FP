import { Component } from '@angular/core';
import {ConnectDBService} from "../../shared/services/connect-db.service";
import {Users} from "../../shared/classes/users";

@Component({
  selector: 'app-api1users',
  templateUrl: './api1users.component.html',
  styleUrl: './api1users.component.css'
})
export class Api1usersComponent {
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
