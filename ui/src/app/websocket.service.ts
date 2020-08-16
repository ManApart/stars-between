import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  public data;

  constructor() {
    const myWebSocket: WebSocketSubject<any> = webSocket('ws://localhost:1235');
    this.data = myWebSocket.asObservable();
  }
}
