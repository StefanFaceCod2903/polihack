import {Injectable} from '@angular/core';
import {HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  url = "http://localhost:8080/api"

  constructor() {
  }

  getHttpOptions() {
    const token = localStorage.getItem('access_token');
    return token ? {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    } : {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
  }
}
