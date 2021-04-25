import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { PlanetService } from 'src/app/planetService';

@Component({
  selector: 'planet-options',
  templateUrl: './planet-options.component.html',
  styleUrls: ['./planet-options.component.css']
})
export class PlanetOptionsComponent implements OnInit {
  seed: number
  randomSeed: boolean
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
    const seed = (this.randomSeed) ? Math.random() * 1000 : this.seed


    const options = {
      seed: seed,
      density: this.density,
      scale: this.scale,

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
