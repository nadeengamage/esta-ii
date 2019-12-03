import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DepartmentService } from 'src/app/services/department/department.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class DepartmentEditComponent implements OnInit {

  editForm: FormGroup;
  submitted = false;
  success: boolean;
  error: boolean;

  constructor(private route: ActivatedRoute, private service: DepartmentService) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.service.getDepartmentById(params['id'])
        .subscribe((res: any) => {
          this.editForm.setValue({
            department_name: res.name,
            working_days: res.workingDaysPerWeek,
            working_hours: res.workingHoursPerDay,
            status: res.status
          });
        })
    });

    this.editForm = new FormGroup({
      department_name: new FormControl('', [Validators.required]),
      working_days: new FormControl('', [Validators.required]),
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
      this.service.updateDepartment(this.editForm.value, params['id'])
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
