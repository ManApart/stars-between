import { Component, OnInit, Input } from '@angular/core'
import { Tile } from './tile'

@Component({
  selector: 'app-tile',
  templateUrl: './tile.component.html',
  styleUrls: ['./tile.component.css']
})
export class TileComponent implements OnInit {
  @Input() tile: Tile
  @Input() mode: string

  private tileSize = 40

  constructor() { }

  ngOnInit(): void {
  }

  getImageUrl(): string {
    return '../../assets/images/tiles/' + this.tile.type.toLowerCase() + '.png'
  }

  getXOffset(): number {
    let offset = 0

    switch (this.tile.adjacency) {
      case 'NONE':
        offset = 2
        break
      case 'ONE_SIDE':
        offset = 1
        break
      case 'TWO_SIDE':
        offset = 0
        break
      case 'CORNER':
        offset = 2
        break
      case 'THREE_SIDE':
        offset = 1
        break
      case 'ALL':
        offset = 0
        break
    }

    return offset * -this.tileSize
  }

  getYOffset(): number {
    let offset = 0

    switch (this.tile.adjacency) {
      case 'NONE':
        offset = 1
        break
      case 'ONE_SIDE':
        offset = 1
        break
      case 'TWO_SIDE':
        offset = 1
        break
      case 'CORNER':
        offset = 0
        break
      case 'THREE_SIDE':
        offset = 0
        break
      case 'ALL':
        offset = 0
        break
    }

    return offset * -this.tileSize
  }

}
