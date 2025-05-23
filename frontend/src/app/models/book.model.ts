import { CategoryBook } from "../enums/category-book.enum";
import { StateBook } from "../enums/state-book.enum";

export interface Book {
    title: string;
    author: string;
    year: string;
    qualification: number;
    category: CategoryBook;
    state: StateBook;
    // score: int, CALIFICACIONE GENERAL DEL LIBRO (PROMEDIO POR CANTIDAD DE PRESTAMOS)
    // 
}
