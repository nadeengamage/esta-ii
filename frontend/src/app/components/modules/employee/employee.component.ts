import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee/employee.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  employees: any;

  dtOptions: DataTables.Settings = {};

  constructor(private service: EmployeeService, private http: HttpClient) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
          .get<DataTablesResponse>(this.service.getEmployees() + '?page=' + dataTablesParameters.start + '&size=' + dataTablesParameters.length)
          .pipe(map((res: any) => {
            return {
              draw: 0,
              recordsTotal: res.totalElements,
              recordsFiltered: res.totalElements,
              data: res.content
            }
          }))
          .subscribe(resp => {
            this.employees = resp.data;
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