import { PetType } from "./petType";

export class Pet{

    id?: number;
    name: string;
    cost: number;
    public petType: PetType;
    
    constructor(name: string, petType: PetType, cost: number){
        this.name = name;
        this.petType = petType;
        this.cost = cost;
        
    }
}