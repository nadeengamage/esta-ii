import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent } from "./components/modules/dashboard/dashboard.component";
import { EmployeeComponent } from './components/modules/employee/employee.component';
import { DepartmentComponent } from './components/modules/department/department.component';
import { AssignerComponent } from './components/modules/assigner/assigner.component';
import { AssignerHistoryComponent } from './components/modules/assigner-history/assigner-history.component';
import { DepartmentAddComponent } from './components/modules/department/add/add.component';
import { DepartmentEditComponent } from './components/modules/department/edit/edit.component';
import { EmployeeAddComponent } from './components/modules/employee/add/add.component';
import { EmployeeDeleteComponent } from './components/modules/employee/delete/delete.component';
import { AssignerAddComponent } from './components/modules/assigner/add/add.component';
import { AssignerEditComponent } from './components/modules/assigner/edit/edit.component';
import { DepartmentDeleteComponent } from './components/modules/department/delete/delete.component';
import { EmployeeEditComponent } from './components/modules/employee/edit/edit.component';
import { AssignerDeleteComponent } from './components/modules/assigner/delete/delete.component';

const routes: Routes = [
    {
        path: '',
        component: DashboardComponent
    },
    {
        path: 'departments',
        component: DepartmentComponent
    },
    {
        path: 'departments/add',
        component: DepartmentAddComponent
    },
    {
        path: 'departments/edit/:id',
        component: DepartmentEditComponent
    },
    {
        path: 'departments/delete/:id',
        component: DepartmentDeleteComponent
    },
    {
        path: 'employees',
        component: EmployeeComponent
    },
    {
        path: 'employees/add',
        component: EmployeeAddComponent
    },
    {
        path: 'employees/edit/:id',
        component: EmployeeEditComponent
    },
    {
        path: 'employees/delete/:id',
        component: EmployeeDeleteComponent
    },
    {
        path: 'assigners',
        component: AssignerComponent
    },
    {
        path: 'assigners/add',
        component: AssignerAddComponent
    },
    {
        path: 'assigners/edit/:id',
        component: AssignerEditComponent
    },
    {
        path: 'assigners/delete/:id',
        component: AssignerDeleteComponent
    },
    {
        path: 'assigner-history',
        component: AssignerHistoryComponent
    }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
    declarations: [],
})
export class AppRoutingModule { }