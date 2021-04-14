import { Component, OnInit } from '@angular/core'
import { FloorPlanService } from '../floorPlanService'

@Component({
  selector: 'main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private floorPlanService: FloorPlanService) { }

  selectedTilePalette = 'space'
  selectedMode = 'OVERVIEW'

  ngOnInit(): void {
  }

  tilePaletteChanged(newTile): void {
    this.selectedTilePalette = newTile
  }

  modeChanged(modeName): void {
    this.selectedMode = modeName
  }

  tileClicked(newTile): void {
    // console.log('setting tile', newTile.x, newTile.y, 'to ', this.selectedTilePalette)
    if (this.selectedMode === 'OVERVIEW' || this.selectedMode === 'AIR' || this.selectedMode === 'POWER') {
      this.floorPlanService.setTile(this.selectedTilePalette, newTile.x, newTile.y).toPromise()
    } else if (this.selectedMode === 'DISTANCE') {
      this.floorPlanService.selectTile(newTile.x, newTile.y).toPromise()
    } else {
      console.log('Unknown mode:', this.selectedMode)
    }
  }

}
