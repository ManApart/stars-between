import { Component, OnInit } from '@angular/core'
import { GameService } from '../gameService'

@Component({
  selector: 'app-system-menu',
  templateUrl: './system-menu.component.html',
  styleUrls: ['./system-menu.component.css']
})
export class SystemMenuComponent implements OnInit {

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
  }


  save() {
    this.gameService.save().toPromise()
  }

  load() {
    this.gameService.load().toPromise()
  }


}
