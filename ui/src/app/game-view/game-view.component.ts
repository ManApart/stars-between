import { Component, OnInit } from '@angular/core';
// import { WebsocketService } from '../websocket.service';
// import { ChatService } from '../chat.service';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Component({
  selector: 'app-game-view',
  templateUrl: './game-view.component.html',
  styleUrls: ['./game-view.component.css']
})
export class GameViewComponent {

  constructor() {
    const myWebSocket: WebSocketSubject<any> = webSocket('ws://localhost:1235');
    myWebSocket.asObservable().subscribe(dataFromServer => {
      console.log('data' + JSON.stringify(dataFromServer));
    });
  }

}
