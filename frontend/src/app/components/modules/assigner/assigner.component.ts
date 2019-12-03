import { Component, OnInit } from '@angular/core';
import { AssignerService } from 'src/app/services/assigner/assigner.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-assigner',
  templateUrl: './assigner.component.html',
  styleUrls: ['./assigner.component.scss']
})
export class AssignerComponent implements OnInit {

  assigners: any;

  dtOptions: DataTables.Settings = {};

  constructor(private service: AssignerService, private http: HttpClient) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      serverSide: true,
      processing: true,
      ajax: (dataTablesParameters: any, callback) => {
        this.http
          .get<DataTablesResponse>(this.service.getAssigners() + '?page=' + dataTablesParameters.start + '&size=' + dataTablesParameters.length)
          .pipe(map((res: any) => {
            return {
              draw: 0,
              recordsTotal: res.totalElements,
              recordsFiltered: res.totalElements,
              data: res.content
            }
          }))
          .subscribe(resp => {
            this.assigners = resp.data;
            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: []
            });
          });
      }
    };
  }

}

class DataTablesResponse {
  data: any[];
  draw: number;
  recordsFiltered: number;
  recordsTotal: number;
}
