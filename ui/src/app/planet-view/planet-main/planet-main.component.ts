import { Component, ElementRef, OnInit } from '@angular/core';
import { PlanetService } from 'src/app/planetService';

@Component({
  selector: 'app-planet-main',
  templateUrl: './planet-main.component.html',
  styleUrls: ['./planet-main.component.css']
})
export class PlanetMainComponent implements OnInit {
  selectedView
  planetImage: any
  selectedX: number
  selectedY: number
  region: any
  imageDisplaySize = 500

  views = []

  ngOnInit(): void {
  }

  constructor(private elRef: ElementRef, private planetService: PlanetService) {
    planetService.getViewTypes().toPromise().then(data => {
      this.views = data as Array<string>
      this.selectedView = data[0]
    })
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


  selectView() {
    console.log(this.selectView)
    this.planetService.changeViewType(this.selectedView).toPromise().then(data => {
      this.createImageFromBlob(data);
    }, error => {
      console.log(error);
    })
  }


  planetGenerated(options) {
    this.planetService.generatePlanet(options).toPromise().then(data => {
      this.createImageFromBlob(data);
    }, error => {
      console.log(error);
    })
  }

  imageClicked(event) {
    const actualSize = (document.querySelector('#planet-pic') as any).naturalWidth
    const scale = this.imageDisplaySize / actualSize
    const rect = document.querySelector('#planet-pic').getBoundingClientRect()
    const x = Math.floor((event.x - rect.left) / scale)
    const y = Math.floor((event.y - rect.top) / scale)

    this.planetService.getRegion(x, y).toPromise().then(data => {
      this.selectedX = x
      this.selectedY = y
      this.region = data
    })
  }

}
