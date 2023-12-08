import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTreasureComponent } from './add-treasure.component';

describe('AddTreasureComponent', () => {
  let component: AddTreasureComponent;
  let fixture: ComponentFixture<AddTreasureComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddTreasureComponent]
    });
    fixture = TestBed.createComponent(AddTreasureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
