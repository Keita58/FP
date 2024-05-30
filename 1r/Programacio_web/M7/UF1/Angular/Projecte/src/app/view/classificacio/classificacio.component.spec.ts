import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificacioComponent } from './classificacio.component';
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { ConnectDBService } from '../../shared/services/connect-db.service';
import { Users } from '../../shared/classes/users';
import {of} from "rxjs";

describe('ClassificacioComponent', () => {
  let component: ClassificacioComponent;
  let fixture: ComponentFixture<ClassificacioComponent>;
  let connectdb : ConnectDBService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClassificacioComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule],
      providers: [ConnectDBService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(ClassificacioComponent);
    component = fixture.componentInstance;
    connectdb = TestBed.inject(ConnectDBService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Comprova classificació', () => {
    const res = [{nom: 'marc@ies-sabadell.cat', punts: 112}];
    spyOn(connectdb, 'getAllUsers').and.returnValue(of(res));
    component.getUsers();
    console.log("Hola")
    fixture.detectChanges();
    console.log("Classificació: " + component.users)
    expect(component.users).toContain(jasmine.objectContaining({nom: 'marc@ies-sabadell.cat', punts: 112}));
  })
});
