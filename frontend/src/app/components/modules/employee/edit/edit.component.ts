import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EmployeeEditComponent implements OnInit {

  editForm: FormGroup;
  submitted = false;
  statusList: any[] | { value: string; }[];
  constructor() { }

  ngOnInit() {
    
    this.statusList = [
      {value: 'Active'},
      {value: 'Inactive'}
    ]

    this.editForm = new FormGroup({
      first_name: new FormControl('', [Validators.required]),
      last_name: new FormControl('', [Validators.required]),
      date_join: new FormControl('', [Validators.required]),
      date_left: new FormControl('', []),
      working_hours: new FormControl('', [Validators.required, Validators.pattern("^\d+:\d{2}:\d{2}$")]),
      status: new FormControl(this.statusList[0])
    });
  }

  get form() { return this.editForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.editForm.invalid) {
      return;
    }

    alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.editForm.value))
  }

}
