import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AliveComponent } from './view/alive/alive.component';
import { Alive2Component } from './view/alive2/alive2.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { GetJugadorsAmbPersonatgesComponent } from './view/get-jugadors-amb-personatges/get-jugadors-amb-personatges.component';
import { GetCartesSenseJugadorComponent } from './view/get-cartes-sense-jugador/get-cartes-sense-jugador.component';
import { GetCartesBangSenseJugadorComponent } from './view/get-cartes-bang-sense-jugador/get-cartes-bang-sense-jugador.component';
import { GetPartidesIsFinalitzadaComponent } from './view/get-partides-is-finalitzada/get-partides-is-finalitzada.component';
import { RetornarJugadorsOrdenatsComponent } from './view/retornar-jugadors-ordenats/retornar-jugadors-ordenats.component';
import { GetJugadorsRolsVidaComponent } from './view/get-jugadors-rols-vida/get-jugadors-rols-vida.component';
import { GetCartesFallasteJugadorComponent } from './view/get-cartes-fallaste-jugador/get-cartes-fallaste-jugador.component';
import { GetCartesJugadorComponent } from './view/get-cartes-jugador/get-cartes-jugador.component';
import { GetJugadorsAmbPersonatgesVidaAltresComponent } from './view/get-jugadors-amb-personatges-vida-altres/get-jugadors-amb-personatges-vida-altres.component';
import { AgafarCartesComponent } from './view/agafar-cartes/agafar-cartes.component';
import { DeixarCartesComponent } from './view/deixar-cartes/deixar-cartes.component';
import { TirarCartesComponent } from './view/tirar-cartes/tirar-cartes.component';
import { ReassignarDistanciesComponent } from './view/reassignar-distancies/reassignar-distancies.component';
import { FinalPartidaComponent } from './view/final-partida/final-partida.component';

@NgModule({
  declarations: [
    AppComponent,
    AliveComponent,
    Alive2Component,
    GetJugadorsAmbPersonatgesComponent,
    GetCartesSenseJugadorComponent,
    GetCartesBangSenseJugadorComponent,
    GetPartidesIsFinalitzadaComponent,
    RetornarJugadorsOrdenatsComponent,
    GetJugadorsRolsVidaComponent,
    GetCartesFallasteJugadorComponent,
    GetCartesJugadorComponent,
    GetJugadorsAmbPersonatgesVidaAltresComponent,
    AgafarCartesComponent,
    DeixarCartesComponent,
    TirarCartesComponent,
    ReassignarDistanciesComponent,
    FinalPartidaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
