import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

export const TOKEN_NAME: string = 'token';
export const ID_NAME: string = 'UID';

interface JwtPayload {
  exp: number;           // Data de expiração do token
  iat: number;           // Data de emissão do token
  ROLE: string;          // Propriedade personalizada 'role'
  [key: string]: any;    // Outras propriedades que podem existir no payload
}

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  getToken(): string {
    let token !: any;
    let stToken !: string;
    token = localStorage.getItem(TOKEN_NAME);

    if(token != null){
      stToken = token;
    }else{
      stToken = '';
    }

    return stToken;
  }

  setToken(token: string): void {
    localStorage.setItem(TOKEN_NAME, token);
  }

  removeToken(): void {
    localStorage.removeItem(TOKEN_NAME);
  }
  
  getTokenExpirationDate(token: string): Date | null {
    const decoded = jwtDecode(token);
    
    if(decoded.exp === undefined) return null;

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }
 
  isTokenExpired(token?: string|null): boolean {
    if(!token){
      token = this.getToken();
    } 

    if(!token) {
      return true;
    }

    const date = this.getTokenExpirationDate(token);
    if(date === undefined || date === null) return false;

    return !(date.valueOf() > new Date().valueOf());

  }

  getTokenRole() : string | null {
    const decoded = jwtDecode<JwtPayload>(this.getToken());

    return decoded.ROLE || null;

  }

  setUID(id : string): void {
    localStorage.setItem(ID_NAME, id);
  }

  getUID(): string {
    let UID !: any;
    let stUID !: string;
    UID = localStorage.getItem(ID_NAME);

    if(UID != null){
      stUID = UID;
    }else{
      stUID = '';
    }

    return stUID;
  }

  removeUID(): void {
    localStorage.removeItem(ID_NAME);
  }



}
