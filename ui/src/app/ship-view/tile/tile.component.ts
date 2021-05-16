import { Component, OnInit, Input } from '@angular/core'

@Component({
  selector: 'tile',
  templateUrl: './tile.component.html',
  styleUrls: ['./tile.component.css']
})
export class TileComponent implements OnInit {
  @Input() tile
  @Input() mode: string

  private tileSize = 40

  constructor() { }

  ngOnInit(): void {
  }

  getImageUrl(): string {
    return '../../assets/images/tiles/' + this.tile.type.toLowerCase() + '.png'
  }

  getCrewmanClass(): string {
    return "engineering"
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
