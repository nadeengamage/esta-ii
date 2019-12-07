import { Component, OnInit } from '@angular/core';
import { DepartmentService } from 'src/app/services/department/department.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.scss']
})


export class DepartmentComponent implements OnInit {

  departments: any;

  dtOptions: DataTables.Settings = {};

  constructor(private service: DepartmentService, private http: HttpClient) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
          .get<DataTablesResponse>(this.service.getDepartments() + '?page=' + dataTablesParameters.start + '&size=' + dataTablesParameters.length)
          .pipe(map((res: any) => {
            return {
              draw: 0,
              recordsTotal: res.totalElements,
              recordsFiltered: res.totalElements,
              data: res.content
            }
          }))
          .subscribe(resp => {
            this.departments = resp.data;
            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: []
            });
          });
      },
      columns: [{ data: 'id' }, { data: 'name' }, { data: 'workingDaysPerWeek' }, { data: 'workingHoursPerDay' }, { data: 'status' }]
    };
  }
}


class DataTablesResponse {
  data: any[];
  draw: number;
  recordsFiltered: number;
  recordsTotal: number;
}
