import { Component, OnInit, Output, EventEmitter } from '@angular/core'
import { GameService } from '../gameService'

@Component({
  selector: 'mode-select',
  templateUrl: './mode-select.component.html',
  styleUrls: ['./mode-select.component.css']
})
export class ModeSelectComponent implements OnInit {

  @Output() modeChanged: EventEmitter<string> = new EventEmitter()

  modeOptions = []
  selected = 'OVERVIEW'

  constructor(private gameService: GameService) {
    this.gameService.getViews().toPromise().then(data => {
      this.modeOptions = data as Array<string>
    })
  }

  ngOnInit(): void {
  }

  changeMode(modeName): void {
    this.selected = modeName
    this.gameService.setView(modeName).toPromise()
    this.modeChanged.emit(modeName)
  }

}
