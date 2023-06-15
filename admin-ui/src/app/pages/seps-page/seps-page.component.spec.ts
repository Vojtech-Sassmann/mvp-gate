import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SepsPageComponent } from './seps-page.component';

describe('SepsPageComponentComponent', () => {
  let component: SepsPageComponent;
  let fixture: ComponentFixture<SepsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SepsPageComponent]
    });
    fixture = TestBed.createComponent(SepsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
