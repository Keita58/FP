import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Api3usersComponent } from './api3users.component';

describe('Api3usersComponent', () => {
  let component: Api3usersComponent;
  let fixture: ComponentFixture<Api3usersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Api3usersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Api3usersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
