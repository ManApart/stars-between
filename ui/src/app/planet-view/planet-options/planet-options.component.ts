import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { PlanetService } from 'src/app/planetService';

@Component({
  selector: 'planet-options',
  templateUrl: './planet-options.component.html',
  styleUrls: ['./planet-options.component.css']
})
export class PlanetOptionsComponent implements OnInit {
  seed: number
  randomSeed: boolean = true
  density: number = 100

  octaves: number = 7
  roughness: number = 0.5
  noiseScale: number = 7

  temperature: number = 50
  temperatureVariance: number = 50
  temperatureFactor: number = 1.4

  precipitation: number = 100
  waterThreshold: number = 0

  biomeTypes: string

  @Output() generatePlanet: EventEmitter<any> = new EventEmitter()

  constructor() { }

  ngOnInit(): void {
  }

  generate() {
    const seed = (this.randomSeed) ? Math.random() * 1000 : this.seed


    const options = {
      seed: seed,
      density: this.density,

      octaves: this.octaves,
      roughness: this.roughness,
      noiseScale: this.noiseScale,

      temperature: this.temperature,
      temperatureVariance: this.temperatureVariance,
      temperatureFactor: this.temperatureFactor,

      precipitation: this.precipitation,
      waterThreshold: this.waterThreshold,
      biomeTypes: this.biomeTypes
    }
    this.generatePlanet.emit(options)
  }

}
