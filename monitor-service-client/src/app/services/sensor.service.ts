import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  private sensorUrl = environment.serverSensorURN;

  constructor(private http: HttpClient, private auth: AuthService) {}

  getAllSensors(): Observable<any> {
    
    return this.http.get<any>(this.sensorUrl)
      .pipe(
        map((response: any) => {

          console.log(response);
          
          return response;
        })
      );
  }

  getSensorById(id:number): Observable<any> {
    
    return this.http.get<any>(this.sensorUrl+id)
      .pipe(
        map((response: any) => {

          console.log(response);
          
          return response;
        })
      );
  }
  deleteSensor(id: number){
    return this.http.delete<any>(this.sensorUrl+id)
    .pipe(
      map((response: any) => {
        console.log(response);
        
        return response;
      })
    );
  }
  saveSensor(body:any){
    
    return this.http.post<any>(this.sensorUrl, body).pipe(
      map((response:any)=>{
        return response;
      })
    )
  }
  updateSensor(body:any){
    
    return this.http.put<any>(this.sensorUrl+body.id, body).pipe(
      map((response:any)=>{
        return response;
      })
    )
  }
}
