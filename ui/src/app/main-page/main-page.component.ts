import { Component, OnInit } from '@angular/core'
import { FloorPlanService } from '../floorPlanService'

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private floorPlanService: FloorPlanService) { }

  selectedTilePalette = 'space'

  ngOnInit(): void {
  }

  selectedTileChanged(newTile): void {
    this.selectedTilePalette = newTile
  }

  tileClicked(newTile): void {
    console.log('setting tile', newTile.x, newTile.y, 'to ', this.selectedTilePalette)
    this.floorPlanService.setTile(this.selectedTilePalette, newTile.x, newTile.y).toPromise()
  }

}
