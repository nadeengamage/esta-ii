import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class EmployeeAddComponent implements OnInit {

  addForm: FormGroup;
  submitted = false;
  statusList: any[] | { value: string; }[];
  success: boolean;
  error: boolean;

  constructor(private service: EmployeeService) { }

  ngOnInit() {
    
    this.statusList = [
      {value: 'Active'},
      {value: 'Inactive'}
    ]

    this.addForm = new FormGroup({
      first_name: new FormControl('', [Validators.required]),
      last_name: new FormControl('', [Validators.required]),
      date_join: new FormControl('', [Validators.required]),
      date_left: new FormControl('', []),
      working_hours: new FormControl('', [Validators.required]),
      status: new FormControl(this.statusList[0])
    });
  }

  get form() { return this.addForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.addForm.invalid) {
      return;
    }
    
    this.service.saveEmployee(this.addForm.value)
        .subscribe(
          data  => {
          this.success = true;
          },
          e  => {
            this.error = true;
          });
  }


}
