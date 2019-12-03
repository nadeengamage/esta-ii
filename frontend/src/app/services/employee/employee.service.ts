import { Injectable } from '@angular/core';
import { EmployeeServiceConst as CONST } from "./employee.service.const";
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  employees: any;

  constructor(private http: HttpClient) { }

  getEmployees() {
    return CONST.API_ENDPOINT;
  }

  getEmployeeList() {
    return this.http.get(CONST.API_ENDPOINT);
  }

  getEmployeeById(id: any) {
    return this.http.get(CONST.API_ENDPOINT + '/' + id);
  }

  saveEmployee(payload: any): Observable<any> {

    var data = {
      firstName: payload.first_name,
      lastName: payload.last_name,
      dateJoin: payload.date_join,
      dateLeft: payload.date_left,
      workingHours: payload.working_hours,
      status: parseInt(payload.status)
    }
    
    return this.http.post(CONST.API_ENDPOINT, data)
  }

  updateEmployee(payload: any, id: any): Observable<any> {
    var data = {
      firstName: payload.first_name,
      lastName: payload.last_name,
      dateJoin: payload.date_join,
      dateLeft: payload.date_left,
      workingHours: payload.working_hours,
      status: parseInt(payload.status)
    }

    return this.http.put(CONST.API_ENDPOINT + '/' + id, data);
  }

  deleteEmployee(id: any): Observable<any> {
    return this.http.delete(CONST.API_ENDPOINT + '/' + id);
  }
}
