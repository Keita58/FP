import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ProjecteModule} from "./shared/modules/projecte/projecte.module";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DirectivesDirective } from './view/directives/directives.directive';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    DirectivesDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ProjecteModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
