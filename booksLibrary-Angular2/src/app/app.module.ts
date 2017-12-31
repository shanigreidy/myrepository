import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BooksComponent } from './components/books/books.component';
import { BookComponent } from './components/book/book.component';
import { BooksService } from './services/books.service';
import { CapitalizePipe } from './pipes/capitalize.pipe';
import { FormsModule }   from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent }  from './app.component';

@NgModule({
  imports:      [ BrowserModule, HttpModule, FormsModule ],
  declarations: [ AppComponent, BooksComponent, BookComponent, CapitalizePipe ],
  bootstrap:    [ AppComponent ],
  providers: [ BooksService ]
})
export class AppModule { }
