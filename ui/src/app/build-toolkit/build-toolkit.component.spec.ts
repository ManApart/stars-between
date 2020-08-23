import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildToolkitComponent } from './build-toolkit.component';

describe('BuildToolkitComponent', () => {
  let component: BuildToolkitComponent;
  let fixture: ComponentFixture<BuildToolkitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildToolkitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildToolkitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
