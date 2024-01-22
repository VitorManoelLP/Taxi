import {TestBed} from "@angular/core/testing";
import {CookieManager} from "../cookie.service";
import {CookieService} from "ngx-cookie-service";
import spyOn = jest.spyOn;

describe('CookieManager', () => {

  let service: CookieManager;
  let cookieService: CookieService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CookieManager, CookieService]
    });
    service = TestBed.inject(CookieManager);
    cookieService = TestBed.inject(CookieService);
  });

  test('should get token by Authorization name', () => {
    let getCookieSpy = spyOn(cookieService, 'get').mockReturnValue('token');
    let token = service.getToken();
    expect(getCookieSpy).toHaveBeenCalledWith('Authorization');
    expect(token).toEqual('token');
  });

  test('should set token by Authorization name', () => {
    let getCookieSpy = spyOn(cookieService, 'set');
    service.setToken('token');
    expect(getCookieSpy).toHaveBeenCalledWith('Authorization', 'token');
  });

});
