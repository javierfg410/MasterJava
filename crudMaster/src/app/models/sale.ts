
import { DatePipe } from '@angular/common'
import { Pet } from "./pet";
import { Customer } from "./customer";
import { User } from "./user";

export class Sale{
    

    id?: number;
    public user: User;
    public customer: Customer;
    public pet: Pet;
    public day: number

    
    constructor(user:User,customer:Customer,pet:Pet, day:number){
        this.user = user;
        this.customer = customer;
        this.pet = pet;
        this.day = day;
    }
}