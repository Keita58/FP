import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistreComponent } from './registre.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ConnectDBService } from '../../shared/services/connect-db.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {of} from "rxjs";

describe('RegistreComponent', () => {
  let component: RegistreComponent;
  let fixture: ComponentFixture<RegistreComponent>;
  let connectdb : ConnectDBService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegistreComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule],
      providers: [ConnectDBService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(RegistreComponent);
    component = fixture.componentInstance;
    connectdb = TestBed.inject(ConnectDBService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Comprovar dades - Registre', () => {
    const res = {data: [{nom: 'AAAAAAAAAAAa@ies-sabadell.cat', password: 'Tetris_123'}]};
    spyOn(connectdb, 'registerUser').and.returnValue(of(res));
    component.registerForm.patchValue({nom: 'AAAAAAAAAAAa@ies-sabadell.cat', password: 'Tetris_123'});
    component.register();
    fixture.detectChanges();
    console.log(component.message);
    expect(component.message).toEqual("L'usuari AAAAAAAAAAAa@ies-sabadell.cat s'ha registrat correctament. \n Ara fes un login amb les mateixes credencials per poder començar a jugar!");
  });
});
