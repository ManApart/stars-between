import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanetMainComponent } from './planet-main.component';

describe('PlanetMainComponent', () => {
  let component: PlanetMainComponent;
  let fixture: ComponentFixture<PlanetMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlanetMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanetMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
