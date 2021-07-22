import { Role } from "./role";


export class User{

    id?: number;
    public role: Role;
    name: string;
    lastname: string;
    username: string;
    password: string;
    phone: number;
    
    constructor(role:Role,name:string,lastname:string,username:string,password:string,phone:number){
        this.role = role;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }
}