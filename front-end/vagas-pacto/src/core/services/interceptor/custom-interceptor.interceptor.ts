import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TOKEN_NAME } from '../login/login.service';

const AUTH_HEADER_KEY = 'Authorization';
const AUTH_PREFIX = 'Bearer';

@Injectable()
export class CustomInterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token = localStorage.getItem(TOKEN_NAME);

    if(token){
      request = request.clone({headers: request.headers.set(AUTH_HEADER_KEY, AUTH_PREFIX + ' ' + token)
                              .set('Content-Type', 'application/json')
                              .set('Accept', '*/*')});
    }else{
      request = request.clone({headers: request.headers.set('Content-Type', 'application/json')
                              .set('Accept', '*/*')});
    }

    return next.handle(request);

    
  }
}
