import { Component, OnInit } from '@angular/core'
import { WebsocketService } from '../websocket.service'
import { Tile } from '../tile/tile'

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent {
  public data: Array<Array<Tile>>

  constructor(websocketService: WebsocketService) {
    websocketService.data.subscribe(data => {
      this.data = data
      console.log(data.tiles[0][0])
    })
  }

}
