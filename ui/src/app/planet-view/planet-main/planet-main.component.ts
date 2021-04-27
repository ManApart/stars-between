import { Component, ElementRef, OnInit } from '@angular/core';
import { PlanetService } from 'src/app/planetService';

@Component({
  selector: 'app-planet-main',
  templateUrl: './planet-main.component.html',
  styleUrls: ['./planet-main.component.css']
})
export class PlanetMainComponent implements OnInit {
  planetImage: any
  selectedX: number
  selectedY: number
  region: any

  ngOnInit(): void {
  }

  constructor(private elRef: ElementRef, private planetService: PlanetService) {
    planetService.getPlanetImage().subscribe(data => {
      this.createImageFromBlob(data);
    }, error => {
      console.log(error);
    })
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.planetImage = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }


  changeView() {
  }


  planetGenerated(options) {
    this.planetService.generatePlanet(options).toPromise().then(data => {
      this.createImageFromBlob(data);
    }, error => {
      console.log(error);
    })
  }

  imageClicked(event) {
    const rect = document.querySelector('#planet-pic').getBoundingClientRect()
    const x = Math.round(event.x - rect.left)
    const y = Math.round(event.y - rect.top)

    this.planetService.getRegion(x, y).toPromise().then(data => {
      this.selectedX = x
      this.selectedY = y
      this.region = data
    })
  }

}
