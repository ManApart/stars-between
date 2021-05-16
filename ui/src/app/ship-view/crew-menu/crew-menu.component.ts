import { Component, OnInit } from '@angular/core';
import { CrewService } from '../crewService';

@Component({
  selector: 'crew-menu',
  templateUrl: './crew-menu.component.html',
  styleUrls: ['./crew-menu.component.css']
})
export class CrewMenuComponent implements OnInit {

  constructor(private crewService: CrewService) { }

  ngOnInit(): void {
  }

  addCrewMan(): void {
    this.crewService.addCrewman().toPromise()
  }

}
