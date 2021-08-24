import { Role } from "./role";


export class User{

    id?: number;
    public roles: Role;
    name: string;
    lastname: string;
    username: string;
    password: string;
    phone: number;
    
    constructor(roles:Role,name:string,lastname:string,username:string,password:string,phone:number){
        this.roles = roles;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }
}