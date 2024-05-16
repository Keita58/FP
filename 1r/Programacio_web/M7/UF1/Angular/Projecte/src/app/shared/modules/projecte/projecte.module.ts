import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginaPrincipalComponent } from '../../../view/pagina-principal/pagina-principal.component';
import { LoginComponent } from '../../../view/login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import { RegistreComponent } from '../../../view/registre/registre.component';
import { ClassificacioComponent } from '../../../view/classificacio/classificacio.component';
import { JocComponent } from '../../../view/joc/joc.component';
import {ConnectDBService} from "../../services/connect-db.service";



@NgModule({
  declarations: [
    PaginaPrincipalComponent,
    LoginComponent,
    RegistreComponent,
    ClassificacioComponent,
    JocComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    PaginaPrincipalComponent,
    LoginComponent,
    RegistreComponent,
    ClassificacioComponent,
    JocComponent
  ],
  providers: []
})
export class ProjecteModule { }
