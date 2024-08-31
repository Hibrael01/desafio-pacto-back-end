import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Usuario } from 'src/core/models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private usuarioUrl: string = 'http://localhost:8080/pacto/auth/register'

  constructor(private http: HttpClient) {}

  createNewUser(usuario: Usuario) : Observable<any> {
    return this.http.post<Usuario>(this.usuarioUrl, usuario).pipe(
      catchError(this.handleError)
    );
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
    return throwError('Não foi possível concluir o cadastro, verifique seus dados!.');
  }

}
