import { Book } from "./book.model";
import { User } from "./user.model";

export interface Loan {
    id: string;
    book: Book;
    loanDate: String;
    returnDate: String | null;
    user: User;
    state: LoanStates;
    score: number;
}

export enum LoanStates {
    WAITING = "WAITING",
    RETURNED = "RETURNED",
    LOANED = "LOANED"
}