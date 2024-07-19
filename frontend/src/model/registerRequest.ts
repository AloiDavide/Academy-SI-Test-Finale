export class RegisterRequest {
    username: string;
    mail: string;
    password: string;

    constructor(username: string, email: string, password: string) {
        this.username = username;
        this.mail = email;
        this.password = password;
    }
}
