import { PerfilUsuario } from "./enum/perfilUsuario.enum";

export class Usuario {
    id !: string;
    nome !: string;
    email !: string;
    password !: string;
    perfil !: PerfilUsuario;
}