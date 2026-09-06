import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAttributesProviderService {
  public PRODUCTSRATEDSTATES_API = 'https://ec2-18-117-76-185.us-east-2.compute.amazonaws.com:8080/eCommerceApi/productsRatedStates';

  constructor(private http: HttpClient, private oauthService: OAuthService) {
  }

  getHeaders(): HttpHeaders {
    return new HttpHeaders().set('Authorization', this.oauthService.authorizationHeader())
  }
  saveHoldings(productId: number): Observable<any> {
    let addStateUrl = this.PRODUCTSRATEDSTATES_API + '/addProductState';
    return this.http.post(addStateUrl, productId,{headers: this.getHeaders()});
  }
  getHoldings(productId: number): Observable<any> {
    let getStatesUrl = this.PRODUCTSRATEDSTATES_API + '/getStateById';
    return this.http.post(getStatesUrl,productId,{headers: this.getHeaders()});
  }
}
