import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PetType } from '../models/petType';

@Injectable({
  providedIn: 'root'
})
export class PetTypeService {

  petURL = 'http://localhost:8080/pettypes/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<PetType[]> {
    return this.httpClient.get<PetType[]>(this.petURL + 'paginate' );
  }
  public show(id: number): Observable<PetType> {
    return this.httpClient.get<PetType>(this.petURL + `${id}` );
  }
  public store(petType: PetType): Observable<any>{
    return this.httpClient.post<any>(this.petURL + 'store', petType);
  }
  public delete(id:number): Observable<any>{
    return this.httpClient.delete<any>(this.petURL + `delete/${id}`);
  }
}
