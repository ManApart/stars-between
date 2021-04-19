import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'shield-panel',
  templateUrl: './shield-panel.component.html',
  styleUrls: ['./shield-panel.component.css']
})
export class ShieldPanelComponent implements OnInit {
  @Input() shield
  @Output() shieldUpdated: EventEmitter<any> = new EventEmitter()

  constructor() { }

  ngOnInit(): void {
  }

  updateDesiredPower(event) {
    this.shield.currentDesiredPower = event.target.value
    this.shieldUpdated.emit(this.shield)
  }

  updateFrequency(event) {
    this.shield.frequency = event.target.value
    this.shieldUpdated.emit(this.shield)
  }

}
