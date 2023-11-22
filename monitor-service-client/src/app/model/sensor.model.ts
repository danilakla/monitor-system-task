export interface Sensor{
    id: number,
    name: string,
    model:string,
    rangeFrom: number,
    rangeTo: number,
    type:SensorType,
    unit: SensorUnit
    description:string,
    location:string,

}



interface SensorType{
    id:number,
    name: string
}

interface SensorUnit{
    id: number,
    name: string
}