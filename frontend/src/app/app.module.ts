import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './components/layout/navbar/navbar.component';
import { SidebarComponent } from './components/layout/sidebar/sidebar.component';
import { DashboardComponent } from './components/modules/dashboard/dashboard.component';
import { EmployeeComponent } from './components/modules/employee/employee.component';
import { DepartmentComponent } from './components/modules/department/department.component';
import { AssignerComponent } from './components/modules/assigner/assigner.component';
import { AssignerHistoryComponent } from './components/modules/assigner-history/assigner-history.component';
import { DepartmentAddComponent } from './components/modules/department/add/add.component';
import { DepartmentEditComponent } from './components/modules/department/edit/edit.component';
import { DepartmentDeleteComponent } from './components/modules/department/delete/delete.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    DashboardComponent,
    EmployeeComponent,
    DepartmentComponent,
    AssignerComponent,
    AssignerHistoryComponent,
    DepartmentAddComponent,
    DepartmentEditComponent,
    DepartmentDeleteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
