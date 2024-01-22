import {HttpContext, HttpHeaders, HttpParams} from "@angular/common/http";

export default class HttpClientParam {

  url: string;
  headers?: HttpHeaders | {
    [header: string]: string | string[];
  };
  context?: HttpContext;
  observe?: 'body';
  params?: HttpParams | {
    [param: string]: string | number | boolean | ReadonlyArray<string | number | boolean>;
  };
  reportProgress?: boolean;
  responseType?: 'json';
  withCredentials: boolean;
  body?: any | null;

  private constructor(url: string, body: any, withCredentials = true) {
    this.url = url;
    this.body = body;
    this.withCredentials = withCredentials;
  }

  public static of(param: { url: string, body: any }): HttpClientParam {
    return new HttpClientParam(param.url, param.body);
  }

}
