import { Component, OnInit } from '@angular/core';
import { HistoryService } from 'src/app/services/assigner/history.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/internal/operators/map';

@Component({
  selector: 'app-assigner-history',
  templateUrl: './assigner-history.component.html',
  styleUrls: ['./assigner-history.component.scss']
})
export class AssignerHistoryComponent implements OnInit {

  histories: any;

  dtOptions: DataTables.Settings = {};

  constructor(private service: HistoryService, private http: HttpClient) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
          .get<DataTablesResponse>(this.service.getAssignerHistory() + '?page=' + dataTablesParameters.start + '&size=' + dataTablesParameters.length)
          .pipe(map((res: any) => {
            return {
              draw: 0,
              recordsTotal: res.totalElements,
              recordsFiltered: res.totalElements,
              data: res.content
            }
          }))
          .subscribe(resp => {
            this.histories = resp.data;
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