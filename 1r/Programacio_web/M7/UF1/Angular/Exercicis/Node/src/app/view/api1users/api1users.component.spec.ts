import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Api1usersComponent } from './api1users.component';

describe('Api1usersComponent', () => {
  let component: Api1usersComponent;
  let fixture: ComponentFixture<Api1usersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Api1usersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Api1usersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
