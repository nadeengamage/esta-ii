import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DepartmentService } from 'src/app/services/department/department.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class DepartmentAddComponent implements OnInit {

  addForm: FormGroup;
  submitted = false;
  statusList;
  success: boolean;
  error: boolean;
  
  constructor(private service: DepartmentService) { }

  ngOnInit() {


    this.addForm = new FormGroup({
      department_name: new FormControl('', [Validators.required]),
      working_days: new FormControl('', [Validators.required]),
      working_hours: new FormControl('', [Validators.required]),
      status: new FormControl(1)
    });
  }

  get form() { return this.addForm.controls; }

  onSubmit() {
    this.submitted = true;

        // stop here if form is invalid
        if (this.addForm.invalid) {
            return;
        }

        // alert('SUCCESS!! :-)\n\n' + JSON.stringify())
        this.service.saveDepartmet(this.addForm.value)
        .subscribe(
          data  => {
          this.success = true;
          },
          e  => {
            this.error = true;
          });
  }

}
