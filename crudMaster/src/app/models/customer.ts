

export class Customer{

    id?: number;
    dni: string;
    name: string;
    lastname: string;
    address: string;
    country: string;
    email: string;
    phone: number;
    
    constructor(dni:string,name:string,lastname:string,address:string,country:string,email:string,phone:number){
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.country = country;
        this.email = email;
        this.phone = phone;
    }
}