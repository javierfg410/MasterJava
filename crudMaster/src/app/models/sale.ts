
import { DatePipe } from '@angular/common'
import { Pet } from "./pet";
import { Customer } from "./customer";
import { User } from "./user";

export class Sale{
    

    id?: number;
    public user: User;
    public customer: Customer;
    public pet: Pet;
    public day: DatePipe

    
    constructor(user:User,customer:Customer,pet:Pet, day:DatePipe){
        this.user = user;
        this.customer = customer;
        this.pet = pet;
        this.day = day;
    }
}