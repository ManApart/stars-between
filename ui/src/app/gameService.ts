import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'

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

  getViews() {
    return this.http.get(`http://localhost:8080/game/views`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  setView(view) {
    console.log(view)

    return this.http.put(`http://localhost:8080/game/views?viewType=${view}`, {
      headers: new HttpHeaders(),
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
