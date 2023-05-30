import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IdpsPageComponent } from './idps-page.component';

describe('IdpsComponent', () => {
  let component: IdpsPageComponent;
  let fixture: ComponentFixture<IdpsPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IdpsPageComponent]
    });
    fixture = TestBed.createComponent(IdpsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
