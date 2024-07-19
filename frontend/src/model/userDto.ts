export class UserDto {
    username: string;
	mail: string;

    constructor(username: string, mail: string) {
        this.username = username;
        this.mail = mail;
    }
}
