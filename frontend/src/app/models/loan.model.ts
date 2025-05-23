import { Book } from "./book.model";

export interface Loan {
    id: string;
    book: Book;
    dateLoan: String;
    dateReturn: String | null;
    // ESTADO DE DEVUELTO O EN USO
    // BOOLEAN DE CALIFICADO, SI TRUE NO PUEDE CALIFICAR DE NUEVO 
    // SCORE, PUNTAJE DE CALIFICACION 
}