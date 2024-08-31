import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PerfilUsuario } from 'src/core/models/enum/perfilUsuario.enum';
import { Usuario } from 'src/core/models/usuario.model';
import { LoginService } from 'src/core/services/login/login.service';
import { RedirectService } from 'src/core/services/redirect/redirect.service';
import { RegisterService } from 'src/core/services/register/register.service';
import { TokenService } from 'src/core/services/token/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  usuario: Usuario = new Usuario();
  hidePassword: boolean = true;

  dadosLogin = this._formBuilder.group({
    email: ['', Validators.required, Validators.email],
    senha: ['', Validators.required],
  })

  constructor(private _formBuilder: FormBuilder, private _loginService: LoginService, private _redirectService: RedirectService,
    private _snackBar: MatSnackBar, private _tokenService: TokenService) {}


  realizarLogin() {
    this.preecheLogin();
    this._loginService.doLogin(this.usuario).subscribe((res: any) => {

      if(res != null && res != undefined) {

        if(res.token != null && res.token != undefined && res.token.length != 0
            && res.userId != null && res.userId != undefined && res.userId.length != 0){

          this._tokenService.setToken(res.token);
          this._tokenService.setUID(res.userId);

          const role = this._tokenService.getTokenRole();

          if(role == PerfilUsuario.ADMINISTRADORES) {
            this._redirectService.redirect('cadastro/vaga');
          }else if(role == PerfilUsuario.COLABORADORES){
            this._redirectService.redirect('vagas');
          }else{
            this._redirectService.redirect('unauthorized');
          }

          this._snackBar.open('Cadastro realizado com sucesso!', undefined, {duration:3000});
        }
          
      }

    }, error => {this._snackBar.open(error, undefined, {duration:3000})}
      )
  }

  iniciarCadastro() {
    this._redirectService.redirect('register');
  }

  private preecheLogin() {
    this.usuario.email = this.dadosLogin.get('email')?.value ?? '';
    this.usuario.password = this.dadosLogin.get('senha')?.value ?? '';
  }

}
