import { CategoryBook } from "../enums/category-book.enum";
import { StateBook } from "../enums/state-book.enum";
import { Loan } from "./loan.model";

export interface Book {
    id: string;
    name: string;
    author: string;
    year: string;
    score: number;
    category: CategoryBook;
    state: StateBook;
    pendingLoans: Loan[];
    // score: int, CALIFICACIONE GENERAL DEL LIBRO (PROMEDIO POR CANTIDAD DE PRESTAMOS)
    // 
}
