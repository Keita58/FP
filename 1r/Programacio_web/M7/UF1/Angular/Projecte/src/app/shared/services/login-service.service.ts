import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor() { }
  userLogin : BehaviorSubject<any> = new BehaviorSubject<any>('logout'); //* El que té el valor del login, per cridar el valor fem getValue()
  updateLoginData(user : any) { //* Per actualitzar el valor del login (quan canviem d'usuari)
    this.userLogin.next(user);
  }
}
