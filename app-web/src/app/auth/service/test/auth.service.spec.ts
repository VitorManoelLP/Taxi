import {TestBed} from "@angular/core/testing";
import {AuthService} from "../auth.service";
import {HttpClientExtensionService} from "../../../core/http-client-extension.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {of} from "rxjs";
import spyOn = jest.spyOn;

describe('AuthService', () => {

  let service: AuthService;
  let httpClientSpy: HttpClientExtensionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthService, HttpClientExtensionService]
    });
    service = TestBed.inject(AuthService);
    httpClientSpy = TestBed.inject(HttpClientExtensionService);
  });

  test('should be created', () => {
    expect(service).toBeTruthy();
  });

  test('should call signIn', () => {
    const spy = spyOn(httpClientSpy, 'post').mockReturnValue(of('token'));
    service.signIn({ email: 'foo@gmail.com', password: '1234' }).subscribe();
    expect(spy).toHaveBeenCalledWith({
      url: '/api/auth/sign-in',
      body: { email: 'foo@gmail.com', password: '1234' },
      withCredentials: true
    });
  });

});
