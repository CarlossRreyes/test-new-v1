export interface Credentials {
    email: string;
    password: string;
}

export interface RegisterUser {
    identificacion?: number;
    nombres?: string;
    apellidos?: string;
    contrasena?: string;
    fechaNacimiento?: Date;
}