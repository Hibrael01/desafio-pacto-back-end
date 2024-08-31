import { ErrorHandler, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RootComponent } from 'src/components/root/root.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRouterModule } from './app-router.module';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import { CustomInterceptorInterceptor } from '../services/interceptor/custom-interceptor.interceptor';
import { AuthErrorHandler } from '../options/auth-error-handler';
import { HomeComponent } from 'src/pages/home/home.component';
import { VagasComponent } from 'src/pages/vagas/vagas.component';
import { CadastroVagasComponent } from 'src/pages/cadastro-vagas/cadastro-vagas.component';
import { RegisterComponent } from 'src/pages/register/register.component';
import { CandidaturaComponent } from 'src/pages/candidatura/candidatura.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { ToolbarComponent } from 'src/components/shared/toolbar/toolbar.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import { UnauthorizadePageComponent } from 'src/pages/error/unauthorized-page/unauthorized-page.component';







@NgModule({
  declarations: [RootComponent, HomeComponent, VagasComponent, CadastroVagasComponent, CandidaturaComponent, RegisterComponent, ToolbarComponent, UnauthorizadePageComponent],
  imports: [CommonModule, FormsModule, BrowserModule, AppRouterModule, MatSnackBarModule, BrowserAnimationsModule, HttpClientModule, MatToolbarModule, ReactiveFormsModule, 
    MatFormFieldModule, MatIconModule, MatInputModule, MatButtonModule, MatSelectModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: CustomInterceptorInterceptor, multi: true},
              {provide: ErrorHandler, useClass: AuthErrorHandler}],
  bootstrap: [RootComponent]
})

export class AppModule { }
