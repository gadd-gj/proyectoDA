import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { retry } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Departamento } from '../model/departamento.model';

@Injectable({
  providedIn: 'root'
})
export class DepartamentoService {
  private url = '/uv';

  constructor(private http: HttpClient) {}


  insert(departamento: Departamento ): Observable<Departamento>{
    return this.http.post<Departamento>(`${this.url}/departamentos`, departamento)
    .pipe(
      retry(1)
    );
  }

  update(departamento: Departamento): Observable<any>{
    const departamentoTemp = {
      nombre: departamento.nombre,
    };
    return this.http.put(`${this.url}/departamentos/${departamento.idDepartamento}`, departamentoTemp);
  }


  showAll(): Observable<Departamento> {
    return this.http.get<Departamento>(`${this.url}/departamentos`)
    .pipe(
      retry(1)
    );
  }
  showById(id: number): Observable<Departamento>{
    return this.http.get<Departamento>(`${this.url}/departamentos/${id}`)
    .pipe(
      retry(1)
    );
  }
  delete(id: number): Observable<any>{
    return this.http.delete(`${this.url}/departamentos/${id}`);
  }
}
