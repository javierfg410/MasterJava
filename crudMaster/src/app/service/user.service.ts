import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userURL = 'http://217.61.97.164:8080/auth/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.userURL + 'users/paginate' );
  }
  public show(id: number): Observable<User> {
    return this.httpClient.get<User>(this.userURL + `users/${id}` );
  }
  public findByUsername(username: string): Observable<User> {
    return this.httpClient.get<User>(this.userURL + `users/username/${username}` );
  }
  public store(user: User): Observable<any>{
    return this.httpClient.post<any>(this.userURL + 'new', user);
  }
  public update(id:number, user: User): Observable<any>{
    return this.httpClient.put<any>(this.userURL + `users/update/${id}`, user);
  }
  public delete(id:number): Observable<any>{
    return this.httpClient.delete<any>(this.userURL + `users/delete/${id}`);
  }
}
