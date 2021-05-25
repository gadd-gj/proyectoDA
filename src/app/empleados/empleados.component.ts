import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Departamento } from '../model/departamento.model';
import { Empleado } from '../model/empleado.model';
import { DepartamentoService } from '../services/departamento.service';
import { EmpleadoService } from '../services/empleado.service';
import Swal from 'sweetalert2';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.css']
})
export class EmpleadosComponent implements OnInit {

  departamento = new Departamento();
  empleado = new Empleado(this.departamento);
  modalEmpleado = new Empleado(this.departamento);
  empleados: Empleado [] = [];
  departamentos: Departamento [] = [];
  busqueda: any;

  constructor(private empleadoService: EmpleadoService, private departamentoService: DepartamentoService) { }

  ngOnInit(): void {
    this.getAll();
    this.getAllDepartamento();

  }

  actionEmpleado( form: NgForm ): void{
    if (form.invalid) {
      return;
    }
    let texto;
    this.empleado.id ? texto = 'Actualizando' : texto = 'Guardando';

    Swal.fire({
      title: 'Espere',
      text: `${texto} Departamento`,
      icon: 'info',
      allowOutsideClick: false,
      showConfirmButton: false
    });
    Swal.isLoading();

    let estado: Observable<any>;

    if (this.empleado.id) {
      estado = this.empleadoService.update(this.empleado);
    }else{
      estado = this.empleadoService.insert(this.empleado);
    }

    estado.subscribe(res => {
      Swal.fire({
        title: `Departamento: ${this.empleado.nombre}`,
        text: 'Guardado Correctamente',
        icon: 'success'
      });
      this.empleado.id ? this.getAll() : this.empleados.push(res);
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
    this.empleados = [];
    this.empleadoService.showById(this.busqueda).subscribe((res: any) => {
      this.empleados.push(res);
    });
  }

  closeModal(idModal: any): void {
    document.getElementById(`${idModal}`)?.click();
  }

  getAll(): void {
    this.empleadoService.showAll().subscribe((empleado: any) => {
       this.empleados = empleado;
    });
   }

   getAllDepartamento(): void{
    this.departamentoService.showAll().subscribe((departameto: any) => {
       this.departamentos = departameto;
    });
   }


   modalData(empleado: any): void{
     this.empleado.id = empleado.id;
     this.empleado.nombre = empleado.nombre;
     this.empleado.direccion = empleado.direccion;
     this.empleado.telefono = empleado.telefono;
     this.empleado.departamento.idDepartamento = empleado.departamento;
   }

  eliminar(empleado: any): void{

    Swal.fire({
      title: `¿Desea Eliminar?`,
      text: `El empleado ${empleado.nombre}`,
      icon: `question`,
      showConfirmButton: true,
      showCancelButton: true
    }).then(resp => {
      if (resp.value) {
        this.empleadoService.delete(empleado.id).subscribe(() => {
          this.getAll();
        });
      }
    });

  }

}
