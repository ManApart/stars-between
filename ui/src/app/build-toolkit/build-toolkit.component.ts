import { Component, OnInit, EventEmitter, Output } from '@angular/core'

@Component({
  selector: 'app-build-toolkit',
  templateUrl: './build-toolkit.component.html',
  styleUrls: ['./build-toolkit.component.css']
})
export class BuildToolkitComponent implements OnInit {

  @Output() tilePaletteChanged: EventEmitter<string> = new EventEmitter()

  constructor() { }
  tileOptions = ['space', 'wall', 'floor', 'vent']
  selected = 'space'

  ngOnInit(): void {
  }

  changeTile(tileName): void {
    this.selected = tileName
    this.tilePaletteChanged.emit(tileName)
  }

}
