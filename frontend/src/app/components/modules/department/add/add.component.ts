import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class DepartmentAddComponent implements OnInit {

  addForm: FormGroup;
  submitted = false;
  statusList;
  
  constructor() { }

  ngOnInit() {

    this.statusList = [
      {value: 'Active'},
      {value: 'Inactive'}
    ]

    this.addForm = new FormGroup({
      department_name: new FormControl('', [Validators.required]),
      working_days: new FormControl('', [Validators.required]),
      working_hours: new FormControl('', [Validators.required, Validators.pattern("^\d+:\d{2}:\d{2}$")]),
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

        alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.addForm.value))
  }

}
