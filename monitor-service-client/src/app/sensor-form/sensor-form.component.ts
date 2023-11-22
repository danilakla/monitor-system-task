import { AuthService } from '../services/auth.service';
// sensor-form.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SensorService } from '../services/sensor.service';
import { UnitService } from '../services/unit.service';
import { SensorTypeService } from '../services/sensor-type.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Sensor } from '../model/sensor.model';


@Component({
  selector: 'app-sensor-form',
  templateUrl: './sensor-form.component.html',
  styleUrls: ['./sensor-form.component.css'],
})
export class SensorFormComponent implements OnInit {
  sensorForm: FormGroup|any;
  types: any[] | undefined;
  units: any[] | undefined;
  sensorUpdate:any;
  successOrFailOperationSaveOrUpdateMessage:string='';
  sensorId:any;
  constructor(
    private fb: FormBuilder,
    private sensorService: SensorService,
    private typeService: SensorTypeService,
    private route: ActivatedRoute,
     private unitService: UnitService,
     private authService:AuthService,
     private router: Router 
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadTypesAndUnits();
    this.sensorId = this.route.snapshot.queryParamMap.get('id');
    if(this.sensorId){
      this.initSensorForUpdate(+this.sensorId)
    }
    
  }

  logoutUser(){
    this.authService.logout();
    this.router.navigate(['/login'])
   }
  private initSensorForUpdate(id:number){
    this.sensorService.getSensorById(id).subscribe(
      (response:Sensor) => {
        // Handle success
        this.sensorUpdate=response; 
        this.sensorForm = this.fb.group({
          id:[response.id],
          name: [response.name, [Validators.required, Validators.maxLength(30)]],
          model: [response.model, [Validators.required, Validators.maxLength(15)]],
          rangeFrom: [response.rangeFrom, Validators.required],
          rangeTo: [response.rangeTo, Validators.required],
          typeId: [response.type.id, Validators.required],
          unitId: [response.unit.id, Validators.required],
          location: [response.location, Validators.maxLength(40)],
          description: [response.description, Validators.maxLength(200)],
        });
      },
      (error) => {
        // Handle error
        console.error('Error saving sensor:', error);
      }
    );
  }

  private initForm(): void {
    this.sensorForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(30)]],
      model: ['', [Validators.required, Validators.maxLength(15)]],
      rangeFrom: [null, Validators.required],
      rangeTo: [null, Validators.required],
      typeId: ['', Validators.required],
      unitId: ['', Validators.required],
      location: ['', Validators.maxLength(40)],
      description: ['', Validators.maxLength(200)],
    });
  }

  private loadTypesAndUnits(): void {
    this.typeService.getAllSensors().subscribe((types) => (this.types = types));
    this.unitService.getAllSensors().subscribe((units) => (this.units = units));
  }

  
  saveSensor(): void {
    if (this?.sensorForm?.valid) {
      if(this.sensorForm.value.rangeFrom>=this.sensorForm.value.rangeTo) {
        this.successOrFailOperationSaveOrUpdateMessage="invalid range "

        return;
      };
      
      if(!this.sensorId){
        this.sensorService.saveSensor(this.sensorForm.value).subscribe(
          (response) => {
            // Handle success
            console.log('Sensor saved successfully:', response);
            this.successOrFailOperationSaveOrUpdateMessage="Sensor was saved successfully"
          },
          (error) => {
            // Handle error
            console.error('Error saving sensor:', error);
            this.successOrFailOperationSaveOrUpdateMessage="Error saving sensor"

          }
        );     
      }else{
        this.sensorService.updateSensor(this.sensorForm.value).subscribe(
          (response) => {
            // Handle success
            console.log('Sensor updated successfully:', response);
            this.successOrFailOperationSaveOrUpdateMessage="Sensor updated successfully"

          },
          (error) => {
            // Handle error
            console.error('Error saving sensor:', error);
            this.successOrFailOperationSaveOrUpdateMessage="Error updating sensor:"

          }
        );   
      }
   
    } else {
      console.log('Form is invalid');
      this.successOrFailOperationSaveOrUpdateMessage="invalid parameters, validation errors"

    }
  }

}
