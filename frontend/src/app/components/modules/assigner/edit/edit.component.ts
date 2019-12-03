import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AssignerService } from 'src/app/services/assigner/assigner.service';
import { DepartmentService } from 'src/app/services/department/department.service';
import { EmployeeService } from 'src/app/services/employee/employee.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class AssignerEditComponent implements OnInit {

  editForm: FormGroup;
  submitted = false;
  success: boolean;
  error: boolean;
  departmentList;
  employeeList;
  errorMessage;

  constructor(private service: AssignerService,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,
    private route: ActivatedRoute) { }

  ngOnInit() {

    this.loadDepartments();
    this.loadEmployees();

    this.route.params.subscribe(params => {
      this.service.getAssignerById(params['id'])
        .subscribe((res: any) => {
          this.editForm.setValue({
            working_hours: res.workingHours,
            departments: res.department.id,
            employees: res.employee.id
          });
        });
    });

    this.editForm = new FormGroup({
      working_hours: new FormControl('', [Validators.required]),
      departments: new FormControl(),
      employees: new FormControl()
    });
  }

  get form() { return this.editForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.editForm.invalid) {
      return;
    }

    var data = {
      workingHours: this.editForm.value.working_hours,
      department: {
        id: this.editForm.value.departments
      },
      employee: {
        id: this.editForm.value.employees
      }
    }

    this.route.params.subscribe(params => {
      this.service.updateAssigner(data, params['id'])
        .subscribe(
          data => {
            this.success = true;
          },
          e => {
            this.errorMessage = e.error.message;
            this.error = true;
          });
    });
  }

  loadDepartments() {
    this.departmentService.getDepartmentList()
      .subscribe((res: any) => {
        this.departmentList = res.content;
      })
  }

  loadEmployees() {
    this.employeeService.getEmployeeList()
      .subscribe((res: any) => {
        this.employeeList = res.content;
      })
  }

}
