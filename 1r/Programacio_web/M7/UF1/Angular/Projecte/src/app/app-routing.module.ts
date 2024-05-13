import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PaginaPrincipalComponent} from "./view/pagina-principal/pagina-principal.component";
import {LoginComponent} from "./view/login/login.component";
import {RegistreComponent} from "./view/registre/registre.component";

const routes: Routes = [
  {path: "", component: PaginaPrincipalComponent},
  {path: "Login", component: LoginComponent},
  {path: "Registre", component: RegistreComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
