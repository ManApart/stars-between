import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core'
import { WebsocketService } from '../websocket.service'
import { Tile } from '../tile/tile'

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent {
  public data: Array<Array<Tile>>

  @Input() mode: string
  @Output() tileClicked: EventEmitter<Tile> = new EventEmitter()

  constructor(websocketService: WebsocketService) {
    websocketService.data.subscribe(wrapper => {
      if (this.data === undefined) {
        this.data = wrapper.data.tiles
      } else {
        wrapper.data.tiles.forEach(row => {
          row.forEach(tile => {
            this.data[tile.y][tile.x] = tile
          })
        })
      }
      // console.log(data.tiles[0][0])
    })
  }

  clickTile(tile): void {
    this.tileClicked.emit(tile)
  }

  trackTile(index, tile) {
    return true
  }

}
