import { Component, Input } from '@angular/core';
import { Injectable }     from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class BooksService{
    constructor(private http: Http){}
 
    getBooks(){ 
        return this.http.get("https://api.myjson.com/bins/18ig0n").map((res) => res.json());
    }
}