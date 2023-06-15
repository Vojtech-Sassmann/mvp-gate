import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SepTableComponent } from './sep-table.component';

describe('SepTableComponent', () => {
  let component: SepTableComponent;
  let fixture: ComponentFixture<SepTableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SepTableComponent]
    });
    fixture = TestBed.createComponent(SepTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
