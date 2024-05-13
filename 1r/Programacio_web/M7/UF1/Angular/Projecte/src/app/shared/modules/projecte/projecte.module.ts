import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginaPrincipalComponent } from '../../../view/pagina-principal/pagina-principal.component';
import { LoginComponent } from '../../../view/login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import { RegistreComponent } from '../../../view/registre/registre.component';



@NgModule({
  declarations: [
    PaginaPrincipalComponent,
    LoginComponent,
    RegistreComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    PaginaPrincipalComponent,
    LoginComponent,
    RegistreComponent
  ]
})
export class ProjecteModule { }
