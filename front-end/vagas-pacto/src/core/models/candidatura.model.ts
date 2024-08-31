import { Usuario } from "./usuario.model";
import { Vaga } from "./vaga.model";

export class Candidatura {
    id !: string;
    vaga !: Vaga;
    usuario !: Usuario;
    dataCandidatura !: Date;
}