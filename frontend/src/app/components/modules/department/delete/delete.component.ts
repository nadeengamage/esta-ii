import { Component, OnInit } from '@angular/core';
import { DepartmentService } from 'src/app/services/department/department.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DepartmentDeleteComponent implements OnInit {

  success: boolean;
  error: boolean;

  constructor(private route: ActivatedRoute, private service: DepartmentService) { }

  ngOnInit() {
  }

  delete() {
    this.route.params.subscribe(params => {
      this.service.deleteDepartment(params['id']).subscribe(
        data => {
          this.success = true;
        },
        e => {
          this.error = true;
        });
    })
  }

}
