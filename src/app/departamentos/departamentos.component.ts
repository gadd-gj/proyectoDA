import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { Departamento } from '../model/departamento.model';
import { DepartamentoService } from '../services/departamento.service';

@Component({
  selector: 'app-departamentos',
  templateUrl: './departamentos.component.html',
  styleUrls: ['./departamentos.component.css']
})
export class DepartamentosComponent implements OnInit {

  departamento = new Departamento();
  departamentos: Departamento [] = [];
  modalDepartamento = new Departamento();
  busqueda: any;

  constructor(private departamentoService: DepartamentoService) { }

  ngOnInit(): void {
      this.getAll();
   }

   actionDepartamento( form: NgForm ): void{
    if (form.invalid) {
        return;
    }
    let texto;
    this.departamento.idDepartamento ? texto = 'Actualizando' : texto = 'Guardando';

    Swal.fire({
      title: 'Espere',
      text: `${texto} Departamento`,
      icon: 'info',
      allowOutsideClick: false,
      showConfirmButton: false
    });
    Swal.isLoading();

    let estado: Observable<any>;

    if (this.departamento.idDepartamento) {
      estado = this.departamentoService.update(this.departamento);
    }else{
      estado = this.departamentoService.insert(this.departamento);
    }

    estado.subscribe(res => {
      Swal.fire({
        title: `Departamento: ${this.departamento.nombre}`,
        text: 'Guardado Correctamente',
        icon: 'success'
      });
      this.departamento.idDepartamento ? this.getAll() : this.departamentos.push(res);
      this.closeModal('closeAction');
      form.reset();
    });

  }

  resetearBusqueda(): void{
    if (this.busqueda === '') {
      this.getAll();
    }else {
      this.buscar();
    }
  }

  buscar(): void{
    this.departamentos = [];
    this.departamentoService.showById(this.busqueda).subscribe((res: any) => {
      this.departamentos.push(res);
    });
  }

  closeModal(idModal: any): void{
    document.getElementById(`${idModal}`)?.click();
  }

  getAll(): void{
    this.departamentoService.showAll().subscribe((departamento: any) => {
       this.departamentos = departamento;
    });
   }

   modalData(departamento: Departamento): void {
     this.departamento.idDepartamento = departamento.idDepartamento;
     this.departamento.nombre = departamento.nombre;
   }

  eliminar(departamento: Departamento): void{
    Swal.fire({
      title: `¿Desea Eliminar?`,
      text: `El empleado ${departamento.nombre}`,
      icon: `question`,
      showConfirmButton: true,
      showCancelButton: true
    }).then(resp => {
      if (resp.value) {
        this.departamentoService.delete(departamento.idDepartamento).subscribe(() => {
          this.getAll();
        });
      }
    });
  }

}
