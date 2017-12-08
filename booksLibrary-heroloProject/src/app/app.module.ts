import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BooksComponent } from './books/books.component';
import { BooksService } from './services/books.service';
import { CapitalizePipe } from './pipes/capitalize.pipe';
import { FormsModule }   from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent }  from './app.component';

@NgModule({
  imports:      [ BrowserModule, HttpModule, FormsModule ],
  declarations: [ AppComponent, BooksComponent, CapitalizePipe ],
  bootstrap:    [ AppComponent ],
  providers: [ BooksService ]
})
export class AppModule { }
