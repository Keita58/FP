import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AliveComponent } from './alive.component';

describe('AliveComponent', () => {
  let component: AliveComponent;
  let fixture: ComponentFixture<AliveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AliveComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AliveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
