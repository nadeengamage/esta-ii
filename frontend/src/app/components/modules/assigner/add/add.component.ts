import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AssignerService } from 'src/app/services/assigner/assigner.service';
import { DepartmentService } from 'src/app/services/department/department.service';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AssignerAddComponent implements OnInit {

  addForm: FormGroup;
  submitted = false;
  success: boolean;
  error: boolean;
  departmentList;
  employeeList;
  errorMessage;

  constructor(private service: AssignerService,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService) { }

  ngOnInit() {

    this.loadDepartments();
    this.loadEmployees();

    this.addForm = new FormGroup({
      working_hours: new FormControl('', [Validators.required]),
      departments: new FormControl(''),
      employees: new FormControl('')
    });
  }

  get form() { return this.addForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.addForm.invalid) {
      return;
    }

    var data = {
      workingHours: this.addForm.value.working_hours,
      department: {
        id: this.addForm.value.departments
      },
      employee: {
        id: this.addForm.value.employees
      }
    }

    this.service.saveAssigner(data)
      .subscribe(
        data => {
          this.success = true;
        },
        e => {
          this.errorMessage = e.error.message;
          this.error = true;
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
