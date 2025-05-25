export interface AddBookRequest {
    name: string,
    author: string,
    category: string,
    year: number
}

export interface AddUserRequest {
    user: string,
    password: string,
    name: string,
}