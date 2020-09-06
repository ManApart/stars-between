import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable()
export class GameService {
  constructor(private http: HttpClient) { }

  save() {
    return this.http.post(`http://localhost:8080/game/save`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  load() {
    return this.http.post(`http://localhost:8080/game/load`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  getTileTypes() {
    return this.http.get(`http://localhost:8080/game/tileTypes`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }
}
