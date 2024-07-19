export class RegisterRequest {
    name: string;
    lastname: string;
    mail: string;
    password: string;
    roles: number[];

    constructor(name: string, lastName: string, email: string, password: string, roles: number[]) {
        this.name = name;
        this.lastname = lastName;
        this.mail = email;
        this.password = password;
        this.roles = roles;
    }
}
