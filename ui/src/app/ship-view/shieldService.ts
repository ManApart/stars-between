import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable()
export class ShieldService {
  constructor(private http: HttpClient) { }

  getShieldControls() {
    return this.http.get(`http://localhost:8080/shield`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  setShieldControls(id, frequency, currentDesiredPower) {
    return this.http.put(`http://localhost:8080/shield`, { id, frequency, currentDesiredPower }, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

}
