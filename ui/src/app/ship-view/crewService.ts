import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable()
export class CrewService {
  constructor(private http: HttpClient) { }

  addCrewman(x, y) {
    return this.http.post(`http://localhost:8080/crew?x=${x}&y=${y}`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

}
