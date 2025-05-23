import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { GlobalStateService } from './services/global-state.service';
import { CategoryBook } from './enums/category-book.enum';
import { StateBook } from './enums/state-book.enum';
import { LibraryServicesService } from './services/library-services.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'InterfazPF';
  private apiLibrary = inject(LibraryServicesService)

  constructor(
    private globalState: GlobalStateService
  ) {
    this.apiLibrary.getHello();
  }

  ngOnInit() {
    const getBooks = [ // // llamar al servicio del backend para obtener todos los libros
      {
        "title": "The Name of the Wind",
        "author": "Patrick Rothfuss",
        "year": "2007",
        "qualification": 4,
        "category": CategoryBook.FANTASY,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "Why Nations Fail",
        "author": "Daron Acemoglu",
        "year": "2012",
        "qualification": 1,
        "category": CategoryBook.HISTORY,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "The Inner Game of Tennis",
        "author": "W. Timothy Gallwey",
        "year": "1834",
        "qualification": 3,
        "category": CategoryBook.ART,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "The Knowledge",
        "author": "Lewis Dartnell",
        "year": "2014",
        "qualification": 2,
        "category": CategoryBook.SCIENCE_FICTION,
        "state": StateBook.LOANED
      },
      {
        "title": "The 48 Laws of Power",
        "author": "Robert Greene",
        "year": "1998",
        "qualification": 5,
        "category": CategoryBook.MYSTERY,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "Evicted",
        "author": "Matthew Desmond",
        "year": "2016",
        "qualification": 4,
        "category": CategoryBook.HISTORY,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "Library",
        "author": "Arthur der Weduwen",
        "year": "2021",
        "qualification": 3,
        "category": CategoryBook.ART,
        "state": StateBook.LOANED
      },
      {
        "title": "Cataloging the world",
        "author": "Alex Wright",
        "year": "2014",
        "qualification": 2,
        "category": CategoryBook.SCIENCE_FICTION,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "The Diamond Age",
        "author": "Neal Stephenson",
        "year": "1995",
        "qualification": 1,
        "category": CategoryBook.FANTASY,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "The anatomy of racial inequality",
        "author": "Glenn C. Loury",
        "year": "2002",
        "qualification": 5,
        "category": CategoryBook.HISTORY,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "The consolations of philosophy",
        "author": "Alain De Botton",
        "year": "2000",
        "qualification": 4,
        "category": CategoryBook.MYSTERY,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "Essais",
        "author": "Michel de Montaigne",
        "year": "1600",
        "qualification": 3,
        "category": CategoryBook.ART,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "Nonviolent Communication",
        "author": "Marshall B. Rosenberg",
        "year": "1999",
        "qualification": 2,
        "category": CategoryBook.ROMANCE,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "Mandarin Chinese",
        "author": "Yong Ho",
        "year": "2007",
        "qualification": 1,
        "category": CategoryBook.COOKING,
        "state": StateBook.LOANED
      },
      {
        "title": "The Secret of Apollo",
        "author": "Stephen B. Johnson",
        "year": "2002",
        "qualification": 5,
        "category": CategoryBook.SCIENCE_FICTION,
        "state": StateBook.AVAILABLE
      },
      {
        "title": "The Mastery and Uses of Fire in Antiquity",
        "author": "J. E. Rehder",
        "year": "2000",
        "qualification": 4,
        "category": CategoryBook.HISTORY,
        "state": StateBook.LOANED
      },
      {
        "title": "Gödel, Escher, Bach",
        "author": "Douglas R. Hofstadter",
        "year": "1979",
        "qualification": 3,
        "category": CategoryBook.ART,
        "state": StateBook.LOANED
      },
      {
        "title": "Algorithms to Live By",
        "author": "Brian Christian",
        "year": "2016",
        "qualification": 2,
        "category": CategoryBook.SCIENCE_FICTION,
        "state": StateBook.AVAILABLE
      }
    ];
    this.globalState.setBooks(getBooks);
  }
}
