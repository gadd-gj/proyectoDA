import { Departamento } from './departamento.model';

export class Empleado {
    id?: number;
    nombre?: string;
    direccion?: string;
    telefono?: string;
    departamento: Departamento;

    constructor(departamento: Departamento){
        this.departamento = departamento;
    }
}
