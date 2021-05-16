import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable()
export class CrewService {
  constructor(private http: HttpClient) { }
  selectedCrewId: number

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

  tileClicked(tile) {
    if (tile.crewManId) {
      this.setSelectedCrewman(tile.crewManId)
    } else {
      this.setOrderTile(tile)
    }
  }


  setSelectedCrewman(id) {
    this.selectedCrewId = id
    console.log('Selected', id)
  }

  setOrderTile(tile) {
    console.log('ordered to tile', tile)
  }

}
