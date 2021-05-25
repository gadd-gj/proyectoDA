import { Injectable } from '@angular/core';
import {map, retry} from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Empleado } from '../model/empleado.model';
import {Departamento} from '../model/departamento.model';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private url = '/uv';
  depa = new Departamento();
  empleado = new Empleado(this.depa);

  constructor(private http: HttpClient) { }
  insert(empleado: Empleado ): Observable<Empleado>{
    return this.http.post<Empleado>(`${this.url}/empleados`, empleado)
    .pipe(
      retry(1)
    );
  }

  update(empleado: Empleado): Observable<any>{
    const empleadoTemp = {
      nombre: empleado.nombre,
      direccion: empleado.direccion,
      telefono: empleado.telefono,
      departamento: {
        idDepartamento: empleado.departamento?.idDepartamento
      }
    };
    return this.http.put(`${this.url}/empleados/${empleado.id}`, empleadoTemp);
  }
  showAll(): Observable<Empleado> {
    return this.http.get<Empleado>(`${this.url}/empleados`)
    .pipe(
      retry(1)
    );
  }
  showById(id: number): Observable<Empleado>{
    return this.http.get<Empleado>(`${this.url}/empleados/${id}`)
      .pipe(
      retry(1)
    );
  }
  delete(id: number): Observable<any>{
    return this.http.delete(`${this.url}/empleados/${id}`);
  }
}
