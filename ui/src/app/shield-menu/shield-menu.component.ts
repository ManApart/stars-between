import { Component, OnInit } from '@angular/core';
import { ShieldService } from '../shieldService';
import { WebsocketService } from '../websocket.service';

@Component({
  selector: 'shield-menu',
  templateUrl: './shield-menu.component.html',
  styleUrls: ['./shield-menu.component.css']
})
export class ShieldMenuComponent implements OnInit {
  public shields: {}
  // public shields: Array<any>
  // public shieldUpdates: {}

  constructor(private websocketService: WebsocketService, private shieldService: ShieldService) {
    this.refreshShields()
    websocketService.data.subscribe(wrapper => {
      let shields = wrapper.data.shields
      if (shields) {
        if (this.shields === undefined) {
          this.shields = wrapper.data.shields
        } else {
          for (let i = 0; i < shields.length; i++) {
            const newShield = shields[i]
            // this.shieldUpdates[newShield.id] = shields[i]
            const existing = this.shields[newShield.id]
            existing.power = newShield.power
            existing.radius = newShield.radius
            this.shields[newShield.id] = existing

            // this.shields[i] = shields[i]
          }
        }
      }
    })
  }

  ngOnInit(): void {
  }

  refreshShields() {
    this.shieldService.getShieldControls().toPromise().then(data => {
      this.shields = {};
      (data as Array<any>).forEach(it => {
        this.shields[it.id] = it
      });
      console.log(this.shields)
    })

  }

  shieldUpdated(shieldControls): void {
    this.shieldService.setShieldControls(shieldControls.id, shieldControls.frequency, shieldControls.currentDesiredPower).toPromise()
  }

}
