import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'

@Injectable()
export class PlanetService {
  constructor(private http: HttpClient) { }

  getPlanetImage() {
    return this.http.get(`http://localhost:8080/planet`, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    })
  }
}
