import { TestBed } from '@angular/core/testing';

import { CustomInterceptorInterceptor } from './custom-interceptor.interceptor';

describe('CustomInterceptorService', () => {
  let service: CustomInterceptorInterceptor;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomInterceptorInterceptor);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
