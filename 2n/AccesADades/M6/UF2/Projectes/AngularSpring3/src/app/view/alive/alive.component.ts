import { Component } from '@angular/core';
import {ConnectService} from '../../shared/services/connect.service';

@Component({
  selector: 'app-alive',
  standalone: false,

  templateUrl: './alive.component.html',
  styleUrl: './alive.component.css'
})
export class AliveComponent {
  message!:string;
  constructor(private connect: ConnectService){};
  resposta() {
    this.connect.getAlive().subscribe(res => {
      this.message = res.estat;

    });
  }
}
