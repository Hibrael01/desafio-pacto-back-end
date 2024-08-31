import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Observable } from "rxjs";
import { TokenService } from "../services/token/token.service";

@Injectable({providedIn: 'root'})
export class AuthGuard {

    constructor(private _tokenService: TokenService, private router: Router, private _snackBar: MatSnackBar) {}

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<any> | Promise<boolean> | UrlTree | boolean {

        const token = this._tokenService.getToken();
        
        if(token === undefined || token === null || token.length == 0) {
            this._snackBar.open("Você não está autenticado!", '', {duration:3000});
            this.router.navigate(['home']);
        }else{
            
            if(this._tokenService.isTokenExpired()) {
                this._snackBar.open("Seu tempo de acesso expirou, realize um novo login!", '', {duration: 3000});
                this.router.navigate(['home']);
                this._tokenService.removeToken();
            }else{
                return true;
            }

        }

        return false;

    }

}