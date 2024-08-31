import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from 'src/pages/home/home.component';
import { VagasComponent } from 'src/pages/vagas/vagas.component';
import { AuthGuard } from '../guards/auth.guard';
import { RegisterComponent } from 'src/pages/register/register.component';
import { CadastroVagasComponent } from 'src/pages/cadastro-vagas/cadastro-vagas.component';
import { Candidatura } from '../models/candidatura.model';
import { CandidaturaComponent } from 'src/pages/candidatura/candidatura.component';
import { UnauthorizadePageComponent } from 'src/pages/error/unauthorized-page/unauthorized-page.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'home'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'cadastro/vaga',
    component: CadastroVagasComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'vagas',
    component: VagasComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'candidatura',
    component: CandidaturaComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'unauthorized',
    component: UnauthorizadePageComponent
  }
]

@NgModule({
  imports : [RouterModule.forRoot(routes, {useHash: false, scrollPositionRestoration: 'enabled'})],
  exports: [RouterModule]
})
export class AppRouterModule { }
