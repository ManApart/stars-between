import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit(): void {
  }

  generate() {
    console.log("generate")
  }

}
