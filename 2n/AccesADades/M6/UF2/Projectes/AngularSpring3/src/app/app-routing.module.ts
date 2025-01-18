import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AliveComponent} from './view/alive/alive.component';
import {Alive2Component} from './view/alive2/alive2.component';
import {
  GetJugadorsAmbPersonatgesComponent
} from './view/get-jugadors-amb-personatges/get-jugadors-amb-personatges.component';
import {GetCartesSenseJugadorComponent} from './view/get-cartes-sense-jugador/get-cartes-sense-jugador.component';
import {
  GetCartesBangSenseJugadorComponent
} from './view/get-cartes-bang-sense-jugador/get-cartes-bang-sense-jugador.component';
import {
  GetPartidesIsFinalitzadaComponent
} from './view/get-partides-is-finalitzada/get-partides-is-finalitzada.component';
import {
  RetornarJugadorsOrdenatsComponent
} from './view/retornar-jugadors-ordenats/retornar-jugadors-ordenats.component';
import {GetJugadorsRolsVidaComponent} from './view/get-jugadors-rols-vida/get-jugadors-rols-vida.component';
import {
  GetCartesFallasteJugadorComponent
} from './view/get-cartes-fallaste-jugador/get-cartes-fallaste-jugador.component';
import {GetCartesJugadorComponent} from './view/get-cartes-jugador/get-cartes-jugador.component';
import {
  GetJugadorsAmbPersonatgesVidaAltresComponent
} from './view/get-jugadors-amb-personatges-vida-altres/get-jugadors-amb-personatges-vida-altres.component';
import {AgafarCartesComponent} from './view/agafar-cartes/agafar-cartes.component';
import {DeixarCartesComponent} from './view/deixar-cartes/deixar-cartes.component';
import {TirarCartesComponent} from './view/tirar-cartes/tirar-cartes.component';
import {ReassignarDistanciesComponent} from './view/reassignar-distancies/reassignar-distancies.component';
import {FinalPartidaComponent} from './view/final-partida/final-partida.component';

const routes: Routes = [
  {path : 'Alive', component : AliveComponent },
  {path : 'JugadorsVius', component : GetJugadorsAmbPersonatgesComponent },
  {path : 'CartesTaula', component : GetCartesSenseJugadorComponent },
  {path : 'BangTaula', component : GetCartesBangSenseJugadorComponent },
  {path : 'PartidesNoAcabades', component : GetPartidesIsFinalitzadaComponent },
  {path : 'JugadorsOrdenats', component : RetornarJugadorsOrdenatsComponent },
  {path : 'JugadorsRols', component : GetJugadorsRolsVidaComponent },
  {path : 'JugadorFallaste', component : GetCartesFallasteJugadorComponent },
  {path : 'JugadorCartes', component : GetCartesJugadorComponent },
  {path : 'JugadorAltresVius', component : GetJugadorsAmbPersonatgesVidaAltresComponent },
  {path : 'AgafarCartes', component : AgafarCartesComponent },
  {path : 'DeixarCartes', component : DeixarCartesComponent },
  {path : 'TirarCartes', component : TirarCartesComponent },
  {path : 'ReassignarDistancies', component :ReassignarDistanciesComponent },
  {path : 'FinalPartida', component : FinalPartidaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
