import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'

@Injectable()
export class PlanetService {
  constructor(private http: HttpClient) { }

  getPlanetImage() {
    return this.http.get(`http://localhost:8080/planet/0`, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    })
  }

  getViewTypes() {
    return this.http.get(`http://localhost:8080/planet/views`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  changeViewType(viewType) {
    return this.http.get(`http://localhost:8080/planet/0/view?view=${viewType}`, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    })
  }

  generatePlanet(options) {
    return this.http.post(`http://localhost:8080/planet/0`, options, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    })
  }

  updateViewOptions(options) {
    return this.http.put(`http://localhost:8080/planet/view`, options, {
      headers: new HttpHeaders({}),
      responseType: 'blob'
    })
  }

  getRegion(x, y) {
    return this.http.get(`http://localhost:8080/planet/0/region/${x}/${y}`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

}
