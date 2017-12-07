import {Component} from '@angular/core';
import {BooksService} from '../services/books.service';
import { CapitalizePipe } from '../pipes/capitalize.pipe';
import {Book} from '../book/bookClass';

@Component({
    selector: 'books',
    templateUrl: 'app/books/books.component.html',
    styleUrls: ['app/books/books.component.css'],
    providers: [BooksService, CapitalizePipe]
})
export class BooksComponent {
    books: book[];
    book: book = new Book('','','');
    hideTitleIsExistsMsg: boolean = true;
    hideInvalidDateMsg: boolean = true;
    editBookTitle: string;
    formTitle: string;
    functionInvoke: string;
    formAction: string;
    hideSuccessMsg: boolean = true;
    successMsgAction: string;

    constructor(private booksService: BooksService, private capitalizePipe: CapitalizePipe){
         booksService.getBooks().subscribe(books => this.books = books);  
    }

    onAddBookButtonClicked(){
        this.formTitle = 'Add new Book';
        this.functionInvoke = 'add';
        this.formAction = 'Add';
    }

    onAddBook(){
        this.book.title = this.capitalizePipe.transform(this.book.title);
        if(this.isValidInput(this.book.title,this.book.author,this.book.date, "addBook")){
            this.onSuccessAddOrEditBook("addBook");
            this.hideTitleIsExistsMsg = true;
            this.hideInvalidDateMsg = true;
            let newBook = new Book(this.book.title,this.book.author,this.book.date);
            this.books.push(newBook); 
            this.resetParameters(); 
        }      
    }

    onEditBookButtonClicked(book:Book){
        this.formTitle = 'Edit Book';
        this.functionInvoke = 'edit';
        this.formAction = 'Save';
        this.editBookTitle= book.title;
        this.book.title = book.title;
        this.book.author = book.author;
        this.book.date = book.date;
    }

    onSaveEditBook(){
        this.book.title = this.capitalizePipe.transform(this.book.title);        
        if(this.isValidInput(this.book.title,this.book.author,this.book.date, "editBook")){
            this.onSuccessAddOrEditBook("editBook");            
            this.hideTitleIsExistsMsg = true;
            this.hideInvalidDateMsg = true;
            for(let index in this.books){
                if(this.books[index].title == this.editBookTitle){
                    this.books[index].title = this.book.title;
                    this.books[index].author = this.book.author;
                    this.books[index].date = this.book.date;
                }
            }
        }  
    }

    onDeleteBook(title:string){
        if (confirm("Are you sure you want to delete this book?") == true) {
            this.books = this.books.filter(book => book.title !== title);
        } 
    }
   
    isValidInput(title:string ,author:string, date:string,action:string):boolean{
        if(this.isTitleExists(this.book.title, action)){
            this.hideTitleIsExistsMsg = false;
            this.hideInvalidDateMsg = true;  

            return false;
        }
        else if(!this.isValidDate(this.book.date)){
            this.hideInvalidDateMsg = false;
            this.hideTitleIsExistsMsg = true;
            
            return false;
        }

        return true;
    }

    isTitleExists(title:string, action:string):boolean{
        for(let book of this.books){
            if(action == "addBook"){
                if(book.title == title){
                    return true;
                }
            }
            else{
                if(book.title !== this.editBookTitle && book.title == title){
                    return true;
                }
            }
        }

        return false;
    }

    isValidDate(date:any):boolean{
        let datePattern = /^\d{2}\/\d{2}\/\d{4}$/;
        let dateArray = date.split("/");

        if(date.match(datePattern)){
            let newDate = new Date(dateArray[2], dateArray[1] - 1, dateArray[0]);
            if (newDate && (newDate.getMonth() + 1) == dateArray[1]){
                return true;
            }
        }

        return false;
    }

    onSuccessAddOrEditBook(action:string){
        this.hideSuccessMsg = false;
        setTimeout(() => this.hideSuccessMsg = true, 2000);

        if(action == "addBook"){
            this.successMsgAction = "added";
        }
        else{
            this.successMsgAction = "saved";
        }
    }

    resetParameters(){
        this.hideTitleIsExistsMsg = true;
        this.hideInvalidDateMsg = true;
        this.book.title = '';       
        this.book.author = '';       
        this.book.date = '';          
    }
}

interface book{
    title: string;
    author: string;
    date: string;
}