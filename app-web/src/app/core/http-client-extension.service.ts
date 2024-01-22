import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import HttpClientParam from "./http-client-param";

@Injectable()
export class HttpClientExtensionService {

  constructor(private httpClient: HttpClient) { }

  public get<T>(request: HttpClientParam): Observable<T> {
    return this.httpClient.get<T>(request.url, request);
  }

  public post<T>(request: HttpClientParam): Observable<T> {
    return this.httpClient.post<T>(request.url, request.body);
  }

  public deleteById<T>(request: HttpClientParam): Observable<T> {
    return this.httpClient.delete<T>(request.url);
  }

}
