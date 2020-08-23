import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameViewComponent } from './game-view/game-view.component';
import { MainPageComponent } from './main-page/main-page.component';
import { TileComponent } from './tile/tile.component';
import { BuildToolkitComponent } from './build-toolkit/build-toolkit.component';

@NgModule({
  declarations: [
    AppComponent,
    GameViewComponent,
    MainPageComponent,
    TileComponent,
    BuildToolkitComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
