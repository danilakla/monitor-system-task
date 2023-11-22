import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SensorService } from '../services/sensor.service';
import { AuthService } from '../services/auth.service';
import { Sensor } from '../model/sensor.model';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']})
export class TableComponent implements OnInit {
  sensors: Sensor[]=[];

  isAdmin :boolean=false;

  constructor(private sensorsService: SensorService, private authService: AuthService,private router: Router) {}
  deleteSensorById(id:number){
  this.sensorsService.deleteSensor(id).subscribe(
    (res)=>{
      console.log(res);
      this.sensors=this.sensors.filter(e=>e.id!=id)
    },
    (error)=>{
      console.log(error);
      
    }
  )
 }

 currentPage = 0; // Current page index
 pageSize = 4; // Number of items per page

 get totalPages(): number {
   return Math.ceil(this.sensors.length / this.pageSize);
 }

 paginateToFirstPage(): void {
   this.currentPage = 0;
 }

 paginateToPreviousPage(): void {
   if (this.currentPage > 0) {
     this.currentPage--;
   }
 }

 paginateToNextPage(): void {
   if (this.currentPage < this.totalPages - 1) {
     this.currentPage++;
   }
 }

 paginateToLastPage(): void {
   this.currentPage = this.totalPages - 1;
 }
 logoutUser(){
  this.authService.logout();
  this.router.navigate(['/login'])
 }
 hasAdminPrivgAdmin(){
  
  return this.isAdmin;
 }
  ngOnInit() {
    this.sensorsService.getAllSensors() .subscribe(
      (response: any[]) => {

        this.sensors=response;
        console.log('Login successful:', response);
      },
      (error) => {
        console.error( error);
      }
    );

    this.authService.getUserRole().subscribe(
      (response: any) => {
        if(response=="Administrator"){
          this.isAdmin=true;

        }else{
          this.isAdmin=false;
        }
      },
      (error) => {
        console.error( error);
        this.isAdmin=false;

      }
    )

  }
}
