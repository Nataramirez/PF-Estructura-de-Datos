import { TypeUser } from "../enums/type-user.enum"

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
    role: TypeUser
}

export interface AuthUserRequest {
    user: string,
    password: string,
}

export interface LoanBookRequest {
    idBook: string,
    userForApply: string
}

export interface ReturnBookRequest {
    userString: string,
    idLoan: string
}

export interface RateBookRequest {
    qualification: number,
    idLoan: string
}

export interface CancelLoanRequest {
    userString: string,
    idLoan: string
}