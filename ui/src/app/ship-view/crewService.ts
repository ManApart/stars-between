import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable()
export class CrewService {
  constructor(private http: HttpClient) { }

  addCrewman() {
    return this.http.post(`http://localhost:8080/crew`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  removeCrewman() {
    return this.http.delete(`http://localhost:8080/`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

}
