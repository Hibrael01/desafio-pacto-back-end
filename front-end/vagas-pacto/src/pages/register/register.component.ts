import { Component } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PerfilUsuario } from 'src/core/models/enum/perfilUsuario.enum';
import { Usuario } from 'src/core/models/usuario.model';
import { RedirectService } from 'src/core/services/redirect/redirect.service';
import { RegisterService } from 'src/core/services/register/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  usuario: Usuario = new Usuario();
  hidePassword: boolean = true;

  dadosUsuario = this._formBuilder.group({
    nome: ['', Validators.required],
    email: ['', Validators.required, Validators.email],
    senha: ['', Validators.required],
    perfil: ['', Validators.required]
  })

  constructor(private _formBuilder: FormBuilder, private _registerService: RegisterService, private _redirectService: RedirectService,
      private _snackBar: MatSnackBar) {}

  finalizarCadastroNovoUsuario() {
    this.preencheUsuario();
    this._registerService.createNewUser(this.usuario).subscribe((res: Usuario) => {

      console.log(res);

      if(res != null && res != undefined) {
        if(res.id != null && res.id != undefined && res.id.length != 0) {
          this._snackBar.open('Cadastro realizado com sucesso!', undefined, {duration:3000});
          
          this._redirectService.redirect('home');

        }
      }

    }, error => {this._snackBar.open(error, undefined, {duration:3000})}
      )
  }

  voltarHome() {
    this.usuario = new Usuario();
    this.hidePassword = true;

    this._redirectService.redirect('home');
  }

  private getPerfilUsuario() : PerfilUsuario {
    if(PerfilUsuario.ADMINISTRADORES == this.dadosUsuario.get('perfil')?.value ?? '') {
      return PerfilUsuario.ADMINISTRADORES;
    }else{
      return PerfilUsuario.COLABORADORES;
    }
  }

  private preencheUsuario() {
    this.usuario.nome = this.dadosUsuario.get('nome')?.value ?? '';
    this.usuario.email = this.dadosUsuario.get('email')?.value ?? '';
    this.usuario.password = this.dadosUsuario.get('senha')?.value ?? '';
    this.usuario.perfil = this.getPerfilUsuario();
  }

}
