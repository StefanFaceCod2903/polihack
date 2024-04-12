import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";
import {ErrorService} from "./error.service";
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs";
import {ItemModel} from "../../shared/item.model";

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private url;
  constructor(private http: HttpClient, private tokenService: HttpService, private errorService: ErrorService) {
    this.url = tokenService.url + "/items"
  }

  getItems() {
    return this.http.get<ItemModel[]>(this.url, this.tokenService.getHttpOptions())
      .pipe(
        catchError(this.errorService.handleError)
      );
  }
}
