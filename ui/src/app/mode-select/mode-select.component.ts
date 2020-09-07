import { Component, OnInit, Output, EventEmitter } from '@angular/core'

@Component({
  selector: 'app-mode-select',
  templateUrl: './mode-select.component.html',
  styleUrls: ['./mode-select.component.css']
})
export class ModeSelectComponent implements OnInit {

  @Output() modeChanged: EventEmitter<string> = new EventEmitter()

  constructor() { }
  modeOptions = ['build', 'air', 'distance', 'power']
  selected = 'build'

  ngOnInit(): void {
  }

  changeMode(modeName): void {
    this.selected = modeName
    this.modeChanged.emit(modeName)
  }

}
