import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PerfilUsuario } from 'src/core/models/enum/perfilUsuario.enum';
import { LoginService } from 'src/core/services/login/login.service';
import { RedirectService } from 'src/core/services/redirect/redirect.service';
import { TokenService } from 'src/core/services/token/token.service';

@Component({
  selector: 'app-candidatura',
  templateUrl: './candidatura.component.html',
  styleUrls: ['./candidatura.component.css']
})
export class CandidaturaComponent implements OnInit {


  constructor(private _formBuilder: FormBuilder, private _loginService: LoginService, private _redirectService: RedirectService,
    private _snackBar: MatSnackBar, private _tokenService: TokenService) {}

  ngOnInit() {
    this.verificarPerfil();
  }

  candidatarNaVaga() {
    
  }

  fazerLogout() {
    const loggedOut = this._loginService.doLogout();

    if(loggedOut){
      this._redirectService.redirect('home');
    }else{
      this._snackBar.open('Não foi possível realizar o logout!', undefined, {duration:3000});
    }

  }

  private verificarPerfil() {
    const perfil = this._tokenService.getTokenRole();

    if(perfil != PerfilUsuario.COLABORADORES){
      this._redirectService.redirect('unauthorized');
    } 

  }


}
