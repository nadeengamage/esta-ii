import { Injectable } from '@angular/core';
import { AssignerServiceConst as CONST } from "./assigner.service.const";

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  constructor() { }

  getAssignerHistory() {
    return CONST.API_ENDPOINT + '/history';
  }
}
