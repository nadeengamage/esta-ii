import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AssignerAddComponent implements OnInit {

  addForm: FormGroup;
  submitted = false;

  constructor() { }

  ngOnInit() {
    
    this.addForm = new FormGroup({
      working_hours: new FormControl('', [Validators.required, Validators.pattern("^\d+:\d{2}:\d{2}$")]),
      departments: new FormControl('', [Validators.required]),
      employees: new FormControl('', [Validators.required])
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
