import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = environment.serverLoginURN;
 static  tokenKey = 'authToken';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const body = { username, password };

    return this.http.post(this.loginUrl+"/login", body)
      .pipe(
        map((response: any) => {
          if (response && response.token) {
            this.saveToken(response.token);
          }
          return response;
        })
      );
  }

  isAuth(): Observable<any> {

    return this.http.get(this.loginUrl+`/is-auth`,)
      .pipe(
        map((response: any) => {
            console.log(response);
            
          return response;
        })
      );
  }
  private saveToken(token: string): void {
    localStorage.setItem(AuthService.tokenKey, token);
  }



  getUserRole(): Observable<any> {
    return this.http.get(this.loginUrl+"/role").pipe(
      map((response:any)=>{
        
        return response[0].authority
      })
    )
  }

  static getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  logout():void{
    localStorage.removeItem(AuthService
      .tokenKey);
    


  }
}