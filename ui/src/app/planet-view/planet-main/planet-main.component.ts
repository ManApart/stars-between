import { Component, OnInit } from '@angular/core';
import { PlanetService } from 'src/app/planetService';

@Component({
  selector: 'app-planet-main',
  templateUrl: './planet-main.component.html',
  styleUrls: ['./planet-main.component.css']
})
export class PlanetMainComponent implements OnInit {
  planetImage: any

  ngOnInit(): void {
  }

  constructor(private planetService: PlanetService) {
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

  planetGenerated(options) {
    console.log("Generating:", options)
    this.planetService.generatePlanet(options).toPromise().then(data => {
      this.createImageFromBlob(data);
    }, error => {
      console.log(error);
    })
  }

}
