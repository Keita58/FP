import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistreComponent } from './registre.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ConnectDBService } from '../../shared/services/connect-db.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('RegistreComponent', () => {
  let component: RegistreComponent;
  let fixture: ComponentFixture<RegistreComponent>;
  let service : ConnectDBService;
  let httpClientSpy : jasmine.SpyObj<HttpClient>;
  let httpClient : HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get','post']);
    service = new ConnectDBService(httpClientSpy);

    TestBed.configureTestingModule({
      declarations: [RegistreComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule],
      providers: [ConnectDBService]
    })
    .compileComponents();
    
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(ConnectDBService);

    fixture = TestBed.createComponent(RegistreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Comprova que la URL sigui correcte - Registre', () => {
    let registerForm = new FormGroup({
      nom : new FormControl('MMarc@ies-sabadell.cat'),
      password : new FormControl('Aaaaaaaaa1')
    });
    service.registerUser(registerForm).subscribe({
      next: Users => {},
      error: fail
    });
    const req = httpTestingController.expectOne(service.REST_API + "/users/insert");
    expect(req.request.url).toEqual('http://localhost:3000/users/insert');
    //console.log(req);
  });

  it('Comprova que el mètode sigui correcte - Registre', () => {
    let registerForm = new FormGroup({
      nom : new FormControl('MMarc@ies-sabadell.cat'),
      password : new FormControl('Aaaaaaaaa1')
    });
    service.registerUser(registerForm).subscribe({
      next: Users => {},
      error: fail
    });
    const req = httpTestingController.expectOne(service.REST_API + "/users/insert");
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual({ nom: 'MMarc@ies-sabadell.cat', password: 'Aaaaaaaaa1' });
  });

  /* it('Comprova els paràmetres enviats - Registre', () => {

  }); */
  
});
