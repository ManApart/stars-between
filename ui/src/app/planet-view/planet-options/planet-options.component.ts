import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { PlanetService } from 'src/app/planetService';

@Component({
  selector: 'planet-options',
  templateUrl: './planet-options.component.html',
  styleUrls: ['./planet-options.component.css']
})
export class PlanetOptionsComponent implements OnInit {
  seed: number
  density: number
  scale: number

  octaves: number
  roughness: number
  noiseScale: number

  temperature: number
  temperatureVariance: number
  temperatureFactor: number

  precipitation: number
  waterThreshold: number

  biomeTypes: string

  @Output() generatePlanet: EventEmitter<any> = new EventEmitter()

  constructor() { }

  ngOnInit(): void {
  }

  generate() {
    const options = { seed: this.seed }
    this.generatePlanet.emit(options)
  }

}
