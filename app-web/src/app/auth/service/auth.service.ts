import {Injectable} from "@angular/core";
import {HttpClientExtensionService} from "../../core/http-client-extension.service";
import {AccountLoginRequest} from "../dto/account-login-request";
import HttpClientParam from "../../core/http-client-param";
import {Observable} from "rxjs";

@Injectable()
export class AuthService {

  constructor(private httpClient: HttpClientExtensionService) {
  }

  public signIn(accountRequest: AccountLoginRequest): Observable<string> {
    return this.httpClient.post<string>(HttpClientParam.of({ url: '/api/auth/sign-in', body: accountRequest }));
  }

}
