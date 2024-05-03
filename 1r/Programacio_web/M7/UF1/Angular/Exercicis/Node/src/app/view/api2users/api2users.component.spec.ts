import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Api2usersComponent } from './api2users.component';

describe('Api2usersComponent', () => {
  let component: Api2usersComponent;
  let fixture: ComponentFixture<Api2usersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Api2usersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Api2usersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
