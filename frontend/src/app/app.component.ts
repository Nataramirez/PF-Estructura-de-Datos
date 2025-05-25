import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { GlobalStateService } from './services/global-state.service';
import { CategoryBook } from './enums/category-book.enum';
import { StateBook } from './enums/state-book.enum';
import { LibraryServicesService } from './services/library-services.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  name = 'InterfazPF';
  private apiLibrary = inject(LibraryServicesService);
  private getBooks = [
    {
      "name": "The Name of the Wind",
      "author": "Patrick Rothfuss",
      "year": "2007",
      "qualification": 4,
      "category": CategoryBook.FANTASY,
      "state": StateBook.AVAILABLE,
      "id": "1"
    },
    {
      "name": "Why Nations Fail",
      "author": "Daron Acemoglu",
      "year": "2012",
      "qualification": 1,
      "category": CategoryBook.HISTORY,
      "state": StateBook.AVAILABLE,
      "id": "2"
    },
    {
      "name": "The Inner Game of Tennis",
      "author": "W. Timothy Gallwey",
      "year": "1834",
      "qualification": 3,
      "category": CategoryBook.ART,
      "state": StateBook.AVAILABLE,
      "id": "3"
    },
    {
      "name": "The Knowledge",
      "author": "Lewis Dartnell",
      "year": "2014",
      "qualification": 2,
      "category": CategoryBook.SCIENCE_FICTION,
      "state": StateBook.LOANED,
      "id": "4"
    },
    {
      "name": "The 48 Laws of Power",
      "author": "Robert Greene",
      "year": "1998",
      "qualification": 5,
      "category": CategoryBook.MYSTERY,
      "state": StateBook.AVAILABLE,
      "id": "5"
    },
    {
      "name": "Evicted",
      "author": "Matthew Desmond",
      "year": "2016",
      "qualification": 4,
      "category": CategoryBook.HISTORY,
      "state": StateBook.AVAILABLE,
      "id": "6"
    },
    {
      "name": "Library",
      "author": "Arthur der Weduwen",
      "year": "2021",
      "qualification": 3,
      "category": CategoryBook.ART,
      "state": StateBook.LOANED,
      "id": "7"
    },
    {
      "name": "Cataloging the world",
      "author": "Alex Wright",
      "year": "2014",
      "qualification": 2,
      "category": CategoryBook.SCIENCE_FICTION,
      "state": StateBook.AVAILABLE,
      "id": "8"
    },
    {
      "name": "The Diamond Age",
      "author": "Neal Stephenson",
      "year": "1995",
      "qualification": 1,
      "category": CategoryBook.FANTASY,
      "state": StateBook.AVAILABLE,
      "id": "9"
    },
    {
      "name": "The anatomy of racial inequality",
      "author": "Glenn C. Loury",
      "year": "2002",
      "qualification": 5,
      "category": CategoryBook.HISTORY,
      "state": StateBook.AVAILABLE,
      "id": "10"
    },
    {
      "name": "The consolations of philosophy",
      "author": "Alain De Botton",
      "year": "2000",
      "qualification": 4,
      "category": CategoryBook.MYSTERY,
      "state": StateBook.AVAILABLE,
      "id": "11"
    },
    {
      "name": "Essais",
      "author": "Michel de Montaigne",
      "year": "1600",
      "qualification": 3,
      "category": CategoryBook.ART,
      "state": StateBook.AVAILABLE,
      "id": "12"
    },
    {
      "name": "Nonviolent Communication",
      "author": "Marshall B. Rosenberg",
      "year": "1999",
      "qualification": 2,
      "category": CategoryBook.ROMANCE,
      "state": StateBook.AVAILABLE,
      "id": "13"
    },
    {
      "name": "Mandarin Chinese",
      "author": "Yong Ho",
      "year": "2007",
      "qualification": 1,
      "category": CategoryBook.COOKING,
      "state": StateBook.LOANED,
      "id": "14"
    },
    {
      "name": "The Secret of Apollo",
      "author": "Stephen B. Johnson",
      "year": "2002",
      "qualification": 5,
      "category": CategoryBook.SCIENCE_FICTION,
      "state": StateBook.AVAILABLE,
      "id": "15"
    },
    {
      "name": "The Mastery and Uses of Fire in Antiquity",
      "author": "J. E. Rehder",
      "year": "2000",
      "qualification": 4,
      "category": CategoryBook.HISTORY,
      "state": StateBook.LOANED,
      "id": "16"
    },
    {
      "name": "Gödel, Escher, Bach",
      "author": "Douglas R. Hofstadter",
      "year": "1979",
      "qualification": 3,
      "category": CategoryBook.ART,
      "state": StateBook.LOANED,
      "id": "17"
    },
    {
      "name": "Algorithms to Live By",
      "author": "Brian Christian",
      "year": "2016",
      "qualification": 2,
      "category": CategoryBook.SCIENCE_FICTION,
      "state": StateBook.AVAILABLE,
      "id": "18"
    }
  ];

  constructor(
    private globalState: GlobalStateService,
    private libraryService: LibraryServicesService
  ) {
    this.apiLibrary.getHello().then((response) => {
      console.log('Hola desde el constructor', response);
    });
  }

  ngAfterViewInit() {
    console.log('ngAfterViewInit called');
    
  }

  async ngOnInit() {

    let allBooks = await this.libraryService.getAllBooks();
    if (allBooks.length < 1) {
        for (const book of this.getBooks) {
          this.libraryService.addBook({
            "name": book.name,
            "author": book.author,
            "year": Number(book.year),
            "category": book.category,
          });
        }
      }
    allBooks = await this.libraryService.getAllBooks();
    this.globalState.setBooks(allBooks);
  }
}
