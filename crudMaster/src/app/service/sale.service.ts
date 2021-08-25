import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sale } from '../models/sale';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  petURL = 'http://217.61.97.164:8080/sales/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Sale[]> {
    return this.httpClient.get<Sale[]>(this.petURL + 'list' );
  }
  public show(id: number): Observable<Sale> {
    return this.httpClient.get<Sale>(this.petURL + `${id}` );
  }
  public type(id: string): Observable<Sale> {
    return this.httpClient.get<Sale>(this.petURL + `user/${id}` );
  }
  public store(sale: Sale): Observable<any>{
    return this.httpClient.post<any>(this.petURL + 'store', sale);
  }


}
