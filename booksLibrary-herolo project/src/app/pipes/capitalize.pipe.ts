import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name: 'capitalize'})
export class CapitalizePipe implements PipeTransform {
    transform(value:any) {
        if (value) {
            let splitValue = value.toLowerCase().split(' ');
            for(let index in splitValue) {
                splitValue[index] = splitValue[index].charAt(0).toUpperCase() + splitValue[index].substring(1);     
            }

            return splitValue.join(' '); 
        }
        
        return value;
    }
}