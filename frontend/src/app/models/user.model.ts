import { TypeUser } from "../enums/type-user.enum";
import { Loan } from "./loan.model";
import { Score } from "./score.model";

export interface User {
    id: String;
    name: string;
    password: string;
    identification: string;
    type: TypeUser;
    loans: Loan[];
    scores: Score[];
}