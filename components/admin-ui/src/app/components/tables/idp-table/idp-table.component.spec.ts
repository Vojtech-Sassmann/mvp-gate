import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IdpTableComponent } from './idp-table.component';

describe('IdpTableComponent', () => {
  let component: IdpTableComponent;
  let fixture: ComponentFixture<IdpTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IdpTableComponent]
    });
    fixture = TestBed.createComponent(IdpTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
