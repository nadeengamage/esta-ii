import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class EmployeeAddComponent implements OnInit {

  addForm: FormGroup;
  submitted = false;
  statusList: any[] | { value: string; }[];
  constructor() { }

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
