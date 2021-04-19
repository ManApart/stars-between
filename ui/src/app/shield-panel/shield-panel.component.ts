import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'shield-panel',
  templateUrl: './shield-panel.component.html',
  styleUrls: ['./shield-panel.component.css']
})
export class ShieldPanelComponent implements OnInit {
  @Input() shield

  constructor() { }

  ngOnInit(): void {
  }

}
