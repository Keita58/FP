import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {FormControl} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ConnectService {

  REST_API: string = 'http://localhost:9001';
  constructor(private httpclient : HttpClient) {

  }
  public getAlive(): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/`);
  }
  public getJugadorsAmbPersonatgesVida(): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getJugadorsAmbPersonatgesVida`);
  }

  public getCartesSenseJugador(): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getCartesSenseJugador`);
  }
  public getCartesBangSenseJugador(): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getCartesBang`);
  }
  public getPartidesNoFinalitzades(): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getPartidesNoFinalitzades`);
  }

  public retornarJugadorsOrdenatsPosicio(): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/retornarJugadorsOrdenatsPosicio`);
  }

  public getJugadorsRolsVida(rol: FormControl): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getJugadorsRolsVida?rol=`+rol);
  }

  public getCartesFallaste(idJugadors:number): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getCartesFallaste?idJugador=`+
      idJugadors);
  }
  public getCartesJugador(idJugadors:number): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getCartesJugador?idJugador=`+
      idJugadors);
  }
  public getJugadorsAmbPersonatgesVidaAltres(idJugadors:number): Observable<any>{
    return this.httpclient.get(`${this.REST_API}/getJugadorsAmbPersonatgesVidaAltres?idJugador=`+
      idJugadors);
  }
  public agafarCartes(idJugadors:number): Observable<any>{
    return this.httpclient.post(`${this.REST_API}/agafarCartes`,idJugadors);
  }

  public deixarCartes(idJugadors:number): Observable<any>{
    return this.httpclient.post(`${this.REST_API}/deixarCartes`,idJugadors);
  }
  public tirarCartes(idJugadors:number): Observable<any>{
    return this.httpclient.post(`${this.REST_API}/tirarCartes`,idJugadors);
  }
  public reassignarDistancies(): Observable<any>{
    return this.httpclient.post(`${this.REST_API}/reassignarDistancies`,"");
  }
  public finalPartida(): Observable<any>{
    return this.httpclient.post(`${this.REST_API}/finalPartida`,"");
  }
}
