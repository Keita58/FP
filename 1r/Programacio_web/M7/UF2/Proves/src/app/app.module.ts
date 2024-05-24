import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ProvesModule} from "./shared/modules/proves/proves.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ProvesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
