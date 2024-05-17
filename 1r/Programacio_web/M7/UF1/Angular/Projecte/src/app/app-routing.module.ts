import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PaginaPrincipalComponent} from "./view/pagina-principal/pagina-principal.component";
import {LoginComponent} from "./view/login/login.component";
import {RegistreComponent} from "./view/registre/registre.component";
import { ClassificacioComponent } from './view/classificacio/classificacio.component';
import { JocComponent } from './view/joc/joc.component';
import { AjudaComponent } from './view/ajuda/ajuda.component';

const routes: Routes = [
  {path: "", component: PaginaPrincipalComponent},
  {path: "Login", component: LoginComponent},
  {path: "Registre", component: RegistreComponent},
  {path: "Classificacio", component: ClassificacioComponent},
  {path: "Joc", component: JocComponent},
  {path: "Ajuda", component: AjudaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
