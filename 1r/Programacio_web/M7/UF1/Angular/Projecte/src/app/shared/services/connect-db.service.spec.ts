import { TestBed } from '@angular/core/testing';

import { ConnectDBService } from './connect-db.service';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

describe('ConnectDBService', () => {
  let service: ConnectDBService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule]
    });
    service = TestBed.inject(ConnectDBService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
