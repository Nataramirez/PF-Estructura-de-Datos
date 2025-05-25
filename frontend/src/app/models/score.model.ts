import { Book } from "./book.model";

export interface Score {
  id: String;
  value: number;
  book: Book;
}