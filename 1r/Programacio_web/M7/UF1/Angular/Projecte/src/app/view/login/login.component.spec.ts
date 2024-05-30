import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ConnectDBService } from '../../shared/services/connect-db.service';
import {of} from "rxjs";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let connectdb : ConnectDBService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule],
      providers: [ConnectDBService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    connectdb = TestBed.inject(ConnectDBService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Comprovar dades - Login', () => {
    const res = {data: [{nom: 'marc@ies-sabadell.cat', password: 'tetris_123'}]};
    spyOn(connectdb, 'getUser').and.returnValue(of(res));
    component.loginForm.patchValue({nom: 'marc@ies-sabadell.cat', password: 'tetris_123'});
    component.login();
    fixture.detectChanges();
    console.log("Data: "+ component.users);
    expect(component.message).toEqual("L'usuari marc@ies-sabadell.cat s'ha logejat correctament.");
  });
});
