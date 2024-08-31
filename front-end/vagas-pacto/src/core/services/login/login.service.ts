import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, pipe, throwError } from 'rxjs';
import { Usuario } from 'src/core/models/usuario.model';
import { TokenService } from '../token/token.service';

export const TOKEN_NAME: string = 'token';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private usuarioUrl: string = 'http://localhost:8080/pacto/auth/login';

  constructor(private http: HttpClient, private _tokenService: TokenService) { }

  doLogin(usuario: Usuario) : Observable<any> {
    return this.http.post<any>(this.usuarioUrl, usuario).pipe(
      catchError(this.handleError)
    );
  }

  doLogout() : boolean {
    this._tokenService.removeToken();
    this._tokenService.removeUID();
    return true;
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    if (error.error instanceof ErrorEvent) {
      // Erro do lado do cliente
      console.error('Ocorreu um erro:', error.error.message);
    } else {
      // O backend retornou um código de status de erro
      console.error(`Código de erro ${error.status}, ` + `mensagem: ${error.error}`);
    }
    // Retorna um observable com uma mensagem de erro amigável
    return throwError('Não foi possível realizar o Login, verifique os dados!.');
  }
}
