import {Component, EventEmitter, Input, Output} from '@angular/core';
import {BookInterface} from '../../interfaces/book/bookInterface';

@Component({
    selector: 'book',
    templateUrl:'app/components/book/book.component.html',
    styleUrls: ['app/components/book/book.component.css'],
})
export class BookComponent {
    @Input() book: BookInterface;
    @Output() bookEdit: EventEmitter<BookInterface> = new EventEmitter<BookInterface>();
    @Output() bookDelete: EventEmitter<string> = new EventEmitter<string>();
   
    onEditBookButtonClicked(book:BookInterface){
        this.bookEdit.emit(book);
    }

    onDeleteBook(bookTitle:string){
        this.bookDelete.emit(bookTitle);
    }
}