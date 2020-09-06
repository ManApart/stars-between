import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable()
export class FloorPlanService {
  constructor(private http: HttpClient) { }

  getFloorPlan() {
    return this.http.get(`http://localhost:8080/floorPlan`, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  setTile(tileTypeName: string, x, y) {
    const tileType = tileTypeName.toUpperCase()
    return this.http.put(`http://localhost:8080/floorPlan`, { tileType, x, y }, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }

  selectTile(x, y) {
    return this.http.post(`http://localhost:8080/floorPlan/tile?x=${x}&y=${y}`, {
      headers: new HttpHeaders({}),
      responseType: 'json',
    })
  }
}
