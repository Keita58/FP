import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {UsersModule} from "./shared/modules/users/users.module";
import {HttpClientModule} from "@angular/common/http";
import {Ex2Module} from "./shared/modules/ex2/ex2.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UsersModule,
    HttpClientModule,
    Ex2Module
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
