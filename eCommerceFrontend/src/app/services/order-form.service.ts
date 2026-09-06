import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { Country } from '../classes/country';
import { Region } from '../classes/region';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class OrderFormService {
  private baseURL = environment.appBaseUrl;

  constructor(private httpClient: HttpClient) { }
  getCountries(): Observable<Country[]> {

    return this.httpClient.get<GetResponseCountries>(`${this.baseURL}/countries`).pipe(
      map(response => response._embedded.countries)
    );
  }

  getRegions(countryName: string): Observable<Region[]> {

    const searchStatesUrl = `${this.baseURL}/regions/search/findByCountryName?name=${countryName}`;

    return this.httpClient.get<GetResponseStates>(searchStatesUrl).pipe(
      map(response => response._embedded.regions)
    );
  }
}
interface GetResponseCountries {
  _embedded: {
    countries: Country[];
  }
}

interface GetResponseStates {
  _embedded: {
    regions: Region[];
  }
}