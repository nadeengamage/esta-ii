import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DepartmentServiceConst as CONST } from "./department.service.const";
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DepartmentService {

  departmens: any;

  constructor(private http: HttpClient) { }

  getDepartments() {
    return CONST.API_ENDPOINT;
  }

  getDepartmentList() {
    return this.http.get(CONST.API_ENDPOINT);
  }

  getDepartmentById(id: any) {
    return this.http.get(CONST.API_ENDPOINT + '/' + id);
  }

  saveDepartmet(payload: any): Observable<any> {
    var data = {
      name: payload.department_name,
      workingDaysPerWeek: payload.working_days,
      workingHoursPerDay: payload.working_hours,
      status: parseInt(payload.status)
    }

    return this.http.post(CONST.API_ENDPOINT, data)
  }

  updateDepartment(payload: any, id: any): Observable<any> {
    var data = {
      name: payload.department_name,
      workingDaysPerWeek: payload.working_days,
      workingHoursPerDay: payload.working_hours,
      status: parseInt(payload.status)
    }

    return this.http.put(CONST.API_ENDPOINT + '/' + id, data);
  }

  deleteDepartment(id: any): Observable<any> {
    return this.http.delete(CONST.API_ENDPOINT + '/' + id);
  }

}