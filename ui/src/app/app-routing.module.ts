import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlanetMainComponent } from './planet-view/planet-main/planet-main.component';
import { MainPageComponent } from './ship-view/main-page/main-page.component';


const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'planet', component: PlanetMainComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
