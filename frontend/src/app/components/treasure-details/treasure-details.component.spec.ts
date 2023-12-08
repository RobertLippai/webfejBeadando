import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreasureDetailsComponent } from './treasure-details.component';

describe('TreasureDetailsComponent', () => {
  let component: TreasureDetailsComponent;
  let fixture: ComponentFixture<TreasureDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TreasureDetailsComponent]
    });
    fixture = TestBed.createComponent(TreasureDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
