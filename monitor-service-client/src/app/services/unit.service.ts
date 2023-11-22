import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UnitService {
  private sensorunitsUrl = environment.serverSensornUnitsURN;

  constructor(private http: HttpClient) {}

  getAllSensors(): Observable<any> {
    
    return this.http.get<any>(this.sensorunitsUrl)
      .pipe(
        map((response: any) => {

          console.log(response);  
          
          return response;
        })
      );
  }
  }
