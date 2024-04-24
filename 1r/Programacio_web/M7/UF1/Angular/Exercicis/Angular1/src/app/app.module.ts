import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {ModulesModule} from "./shared/modules/modules.module";
import {Ex2Module} from "./shared/modules/ex2.module";
import {Ex3Module} from "./shared/modules/ex3.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ModulesModule,
    Ex2Module,
    Ex3Module
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
