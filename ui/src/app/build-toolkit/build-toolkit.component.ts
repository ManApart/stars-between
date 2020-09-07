import { Component, OnInit, EventEmitter, Output } from '@angular/core'
import { GameService } from '../gameService'

@Component({
  selector: 'app-build-toolkit',
  templateUrl: './build-toolkit.component.html',
  styleUrls: ['./build-toolkit.component.css']
})
export class BuildToolkitComponent implements OnInit {

  @Output() tilePaletteChanged: EventEmitter<string> = new EventEmitter()

  tileOptions = []
  selected = 'space'

  constructor(private gameService: GameService) {
    this.gameService.getTileTypes().toPromise().then(data => {
      this.tileOptions = data as Array<string>
    })
  }

  ngOnInit(): void {
  }

  changeTile(tileName): void {
    this.selected = tileName
    this.tilePaletteChanged.emit(tileName)
  }

}
