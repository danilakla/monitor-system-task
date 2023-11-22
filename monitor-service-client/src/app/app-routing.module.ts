import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guard/auth.guard';
import { TableComponent } from './table/table.component';
import { SensorFormComponent } from './sensor-form/sensor-form.component';
import { AdminGuard } from './guard/admin.guard';

const routes: Routes = [
  { path: 'table', component: TableComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'sensor-form', component: SensorFormComponent, canActivate:[AuthGuard, AdminGuard ] },
  { path: '**', redirectTo: 'login', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
