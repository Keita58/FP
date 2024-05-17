import { Component } from '@angular/core';
import { LoginServiceService } from './shared/services/login-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Projecte';
  usuari : any;
  
  constructor(private loginService : LoginServiceService) {
    this.loginService.userLogin.subscribe((data : string) => this.usuari = data);
  }

  ngOnInit() {
    this.usuari = this.loginService.userLogin.getValue();
  }
}
