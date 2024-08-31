import { Usuario } from "./usuario.model";

export class Vaga {
    id !: string;
    titulo !: string;
    descricao !: string;
    usuario !: Usuario;
    dataCadastro !: Date;
}