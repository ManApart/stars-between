import { Component, OnInit } from '@angular/core';
import { WebsocketService } from '../websocket.service';

@Component({
  selector: 'shield-menu',
  templateUrl: './shield-menu.component.html',
  styleUrls: ['./shield-menu.component.css']
})
export class ShieldMenuComponent implements OnInit {
  public shields: Array<any>

  constructor(websocketService: WebsocketService) {
    websocketService.data.subscribe(wrapper => {
      let shields = wrapper.data.shields
      if (shields) {
        if (this.shields === undefined) {
          this.shields = wrapper.data.shields
        } else {
          for (let i = 0; i < shields.length; i++) {
            this.shields[i] = shields[i]
          }
          this.shields.length = shields.length
        }
      }
    })
  }

  ngOnInit(): void {
  }

}
