import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pet } from '../models/pet';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  petURL = 'http://localhost:8080/pets/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Pet[]> {
    return this.httpClient.get<Pet[]>(this.petURL + 'paginate' );
  }
  public show(id: number): Observable<Pet> {
    return this.httpClient.get<Pet>(this.petURL + `${id}` );
  }
  public showType(id: number): Observable<Pet[]> {
    return this.httpClient.get<Pet[]>(this.petURL + `type/${id}` );
  }
  public user(id: number): Observable<Pet[]> {
    return this.httpClient.get<Pet[]>(this.petURL + `user/${id}` );
  }
  public type(type: string): Observable<Pet> {
    return this.httpClient.get<Pet>(this.petURL + `type/${type}` );
  }
  public store(pet: Pet): Observable<any>{
    return this.httpClient.post<any>(this.petURL + 'store', pet);
  }
  public update(id:number, pet: Pet): Observable<any>{
    return this.httpClient.put<any>(this.petURL + `update/${id}`, pet);
  }
  public delete(id:number): Observable<any>{
    return this.httpClient.delete<any>(this.petURL + `delete/${id}`);
  }
}
