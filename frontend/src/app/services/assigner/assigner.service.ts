import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AssignerServiceConst as CONST } from "./assigner.service.const";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssignerService {

  assigners: any;

  constructor(private http: HttpClient) { }

  getAssigners() {
    return CONST.API_ENDPOINT;
  }

  getAssignerById(id: any) {
    return this.http.get(CONST.API_ENDPOINT + '/' + id);
  }

  saveAssigner(payload: any): Observable<any> {
    return this.http.post(CONST.API_ENDPOINT, payload)
  }

  updateAssigner(payload: any, id: any): Observable<any> {
    return this.http.put(CONST.API_ENDPOINT + '/' + id, payload);
  }

  deleteAssigner(id: any): Observable<any> {
    return this.http.delete(CONST.API_ENDPOINT + '/' + id);
  }
}
