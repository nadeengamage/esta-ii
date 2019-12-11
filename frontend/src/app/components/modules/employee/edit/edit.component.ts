import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EmployeeEditComponent implements OnInit {

  editForm: FormGroup;
  submitted = false;
  statusList: any[] | { value: string; }[];
  success: boolean;
  error: boolean;

  constructor(private route: ActivatedRoute, private service: EmployeeService) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.service.getEmployeeById(params.id)
        .subscribe((res: any) => {
          this.editForm.setValue({
            first_name: res.firstName,
            last_name: res.lastName,
            date_join: res.dateJoin,
            date_left: '',
            working_hours: res.workingHours,
            status: res.status,
          });
        })
    });

    this.editForm = new FormGroup({
      first_name: new FormControl('', [Validators.required]),
      last_name: new FormControl('', [Validators.required]),
      date_join: new FormControl('', [Validators.required]),
      date_left: new FormControl('', []),
      working_hours: new FormControl('', [Validators.required]),
      status: new FormControl(1)
    });
  }

  get form() { return this.editForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.editForm.invalid) {
      return;
    }

    this.route.params.subscribe(params => {
      this.service.updateEmployee(this.editForm.value, params['id'])
        .subscribe(
          data => {
            this.success = true;
          },
          e => {
            this.error = true;
          });
    });

  }

}
