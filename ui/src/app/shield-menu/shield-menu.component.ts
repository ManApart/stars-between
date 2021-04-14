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
      if (wrapper.data.shields) {
        this.shields = wrapper.data.shields
        // console.log(wrapper.data.shields)
      }
    })
  }

  ngOnInit(): void {
  }

}
