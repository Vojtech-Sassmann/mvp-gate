import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateIdpDialogComponent } from './create-idp-dialog.component';

describe('CreateIdpDialogComponent', () => {
  let component: CreateIdpDialogComponent;
  let fixture: ComponentFixture<CreateIdpDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateIdpDialogComponent]
    });
    fixture = TestBed.createComponent(CreateIdpDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
