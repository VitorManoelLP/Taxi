import {Injectable} from "@angular/core";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class CookieManager {

  private readonly TOKEN = 'Authorization';

  constructor(private cookieService: CookieService) {
  }

  public getToken(): string {
    return this.get(this.TOKEN);
  }

  public setToken(token: string) {
    this.set(
      this.TOKEN,
      token
    );
  }

  public get(name: string) {
    return this.cookieService.get(name);
  }

  public set(name: string, value: string) {
    this.cookieService.set(name, value);
  }

}
