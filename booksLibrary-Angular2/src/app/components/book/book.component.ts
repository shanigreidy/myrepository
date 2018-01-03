import {Component, EventEmitter, Input, Output} from '@angular/core';
import {BookClass} from '../../classes/book/bookClass';

@Component({
    selector: 'book',
    templateUrl:'app/components/book/book.component.html',
    styleUrls: ['app/components/book/book.component.css'],
})
export class BookComponent {
    @Input()  book: BookClass;
    @Output() bookEdit: EventEmitter<BookClass> = new EventEmitter<BookClass>();
    @Output() bookDelete: EventEmitter<string> = new EventEmitter<string>();
   
    onEditBookButtonClicked(book:BookClass):void{
        this.bookEdit.emit(book);
    }

    onDeleteBook(bookTitle:string):void{
        this.bookDelete.emit(bookTitle);
    }
}