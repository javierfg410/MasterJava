import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  petURL = 'http://localhost:8080/users/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.petURL + 'list' );
  }
  public show(id: number): Observable<User> {
    return this.httpClient.get<User>(this.petURL + `${id}` );
  }
  public store(user: User): Observable<any>{
    return this.httpClient.post<any>(this.petURL + 'store', user);
  }
  public update(id:number, user: User): Observable<any>{
    return this.httpClient.put<any>(this.petURL + `update/${id}`, user);
  }
  public delete(id:number): Observable<any>{
    return this.httpClient.delete<any>(this.petURL + `delete/${id}`);
  }
}
