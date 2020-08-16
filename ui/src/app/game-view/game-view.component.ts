import { Component, OnInit } from '@angular/core';
import { WebsocketService } from '../websocket.service';

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent {
  private data;

  constructor(websocketService: WebsocketService) {
    websocketService.data.subscribe(data => {
      this.data = data;
      console.log(data.tiles);
    });
  }

}
