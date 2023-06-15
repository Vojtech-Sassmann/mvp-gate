import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSepDialogComponent } from './create-sep-dialog.component';

describe('CreateSepDialogComponent', () => {
  let component: CreateSepDialogComponent;
  let fixture: ComponentFixture<CreateSepDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateSepDialogComponent]
    });
    fixture = TestBed.createComponent(CreateSepDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
