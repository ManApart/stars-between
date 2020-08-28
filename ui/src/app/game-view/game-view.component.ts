import { Component, OnInit, EventEmitter, Output } from '@angular/core'
import { WebsocketService } from '../websocket.service'
import { Tile } from '../tile/tile'

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent {
  public data: Array<Array<Tile>>

  @Output() tileClicked: EventEmitter<Tile> = new EventEmitter()

  constructor(websocketService: WebsocketService) {
    websocketService.data.subscribe(data => {
      if (this.data === undefined) {
        this.data = data.tiles
      } else {
        data.tiles.forEach(row => {
          row.forEach(tile => {
            this.data[tile.y][tile.x] = tile
          })
        })
      }
      // console.log(data.tiles[0][0])
    })
  }

  clickTile(tile): void {
    console.log('clicked', tile)
    this.tileClicked.emit(tile)
  }

  trackTile(index, tile) {
    return true
  }

}
