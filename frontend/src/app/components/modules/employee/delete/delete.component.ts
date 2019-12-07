import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class EmployeeDeleteComponent implements OnInit {

  success: boolean;
  error: boolean;

  constructor(private route: ActivatedRoute, private service: EmployeeService) { }

  ngOnInit() {
  }

  delete() {
    this.route.params.subscribe(params => {
      this.service.deleteEmployee(params['id']).subscribe(
        data => {
          this.success = true;
        },
        e => {
          this.error = true;
        });
    })
  }

}
