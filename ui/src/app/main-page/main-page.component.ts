import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor() { }

  selectedTilePalette = 'space'

  ngOnInit(): void {
  }

  selectedTileChanged(newTile): void {
    console.log('event', newTile)
    this.selectedTilePalette = newTile
  }

  tileClicked(newTile): void {
    console.log('setting tile', newTile.x, newTile.y, 'to ', this.selectedTilePalette)
  }

}
