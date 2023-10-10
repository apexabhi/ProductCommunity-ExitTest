import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor( private _http:HttpClient) { }

  postUser(data:any) {
    return this._http.post<any>("http://localhost:8090/user/register", data).pipe(map((res:any)=>{
      return res;
    }))
  } 

  deleteUser(email:string) {
    return this._http.delete<any>("http://localhost:8090/user/" + email).pipe(map((res:any)=>{
      return res;
    }))
  } 
}
