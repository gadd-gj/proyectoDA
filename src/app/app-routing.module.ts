import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DepartamentosComponent } from './departamentos/departamentos.component';
import { EmpleadosComponent } from './empleados/empleados.component';

const routes: Routes = [
  {path:'', component: EmpleadosComponent},
  {path:'empleado', component: EmpleadosComponent},
  {path:'departamento', component: DepartamentosComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
