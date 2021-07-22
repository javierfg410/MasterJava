import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  petURL = 'http://localhost:8080/customers/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(this.petURL + 'list' );
  }
  public show(id: number): Observable<Customer> {
    return this.httpClient.get<Customer>(this.petURL + `${id}` );
  }
  public dni(dni: string): Observable<Customer> {
    return this.httpClient.get<Customer>(this.petURL + `dni/${dni}` );
  }
  public store(customer: Customer): Observable<any>{
    return this.httpClient.post<any>(this.petURL + 'store', customer);
  }
  public update(id:number, customer: Customer): Observable<any>{
    return this.httpClient.put<any>(this.petURL + `update/${id}`, customer);
  }
  public delete(id:number): Observable<any>{
    return this.httpClient.delete<any>(this.petURL + `delete/${id}`);
  }

}
