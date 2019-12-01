import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class AssignerEditComponent implements OnInit {

  editForm: FormGroup;
  submitted = false;

  constructor() { }

  ngOnInit() {
    
    this.editForm = new FormGroup({
      working_hours: new FormControl('', [Validators.required, Validators.pattern("^\d+:\d{2}:\d{2}$")]),
      departments: new FormControl('', [Validators.required]),
      employees: new FormControl('', [Validators.required])
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
