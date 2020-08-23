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

  setTile(tileType, x, y) {
    return this.http.put(`http://localhost:8080/floorPlan`, { tileType, x, y }, {
      headers: new HttpHeaders({}),
      responseType: 'json'
    })
  }
}
