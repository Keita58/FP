import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Jugadors} from '../classes/jugadors';

@Injectable({
  providedIn: 'root'
})
export class JugadorsService {
  private dataSubject = new BehaviorSubject<Jugadors>({idJugador:[0,0,0,0,0,0,0,0]});
  public data$ = this.dataSubject.asObservable();

  updateData(newData: Jugadors) {
    this.dataSubject.next(newData);
  }
  constructor() { }
}
