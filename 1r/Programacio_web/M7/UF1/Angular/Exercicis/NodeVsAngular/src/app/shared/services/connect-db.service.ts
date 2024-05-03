import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ConnectDBService {

  REST_API : string = 'http://localhost:3000';
  constructor(private httpclient : HttpClient) { }

  public getAllUsers() : Observable<any> {
    return this.httpclient.get(`${this.REST_API}/users`)
  }

  public getUser(form : FormGroup) : Observable<any> {
    return this.httpclient.get(`${this.REST_API}/user`, {
      params: {
        nom : form.controls['nom'].value,
        password : form.controls['password'].value
      }
    });
  }

  public registerUser(form : FormGroup) : Observable<any> {
    return this.httpclient.post(`${this.REST_API}/users/insert`, form.value);
  }
}
