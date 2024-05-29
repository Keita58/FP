import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ConnectDBService } from '../../shared/services/connect-db.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let service : ConnectDBService;
  let httpClientSpy : jasmine.SpyObj<HttpClient>;
  let httpClient : HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get','post']);
    service = new ConnectDBService(httpClientSpy);

    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule],
      providers: [ConnectDBService]
    })
    .compileComponents();

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(ConnectDBService);
    
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Comprova que el mètode sigui correcte - Login', () => {
    let loginForm = new FormGroup({
      nom : new FormControl('marc@ies-sabadell.cat'),
      password : new FormControl('tetris_123')
    });

    service.getUser(loginForm).subscribe({
      next: Users => {},
      error: fail
    });

    const req = httpTestingController.expectOne(service.REST_API + "/user?nom=marc@ies-sabadell.cat&password=tetris_123");
    //console.log("Holiis + " + req.request.body);
    expect(req.request.method).toEqual('GET');
    expect(req.request.body).toEqual({ /* _id: new ObjectId('662d455ae62407c6d644fb1f'), */ id: 1, nom: 'marc@ies-sabadell.cat', password: 'tetris_123', punts: 112 });
  });
});
