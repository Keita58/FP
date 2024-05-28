import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificacioComponent } from './classificacio.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { ConnectDBService } from '../../shared/services/connect-db.service';
import { Users } from '../../shared/classes/users';

describe('ClassificacioComponent', () => {
  let component: ClassificacioComponent;
  let fixture: ComponentFixture<ClassificacioComponent>;
  let service : ConnectDBService;
  let httpClientSpy : jasmine.SpyObj<HttpClient>;
  let httpClient : HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get','post']);
    service = new ConnectDBService(httpClientSpy);

    TestBed.configureTestingModule({
      declarations: [ClassificacioComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule],
      providers: [ConnectDBService]
    })
    .compileComponents();
    
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(ConnectDBService);

    fixture = TestBed.createComponent(ClassificacioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Comprova la classificació sigui un GET', () => {
    service.getAllUsers().subscribe({
      next: Users => {},
      error: fail
    });
    const req = httpTestingController.expectOne(service.REST_API + "/users");
    expect(req.request.method).toEqual('GET');
  })
});
