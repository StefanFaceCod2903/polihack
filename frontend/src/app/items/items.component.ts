import { Component } from '@angular/core';
import {ItemModel} from "../shared/item.model";
import {ItemService} from "../core/service/item.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrl: './items.component.css'
})
export class ItemsComponent {
  items : ItemModel[] = [];
  private itemsSubscription = new Subscription();
  constructor(private itemService : ItemService, private router: Router) {
  }

  ngOnInit() {
    this.items = [];
    this.itemsSubscription = this.itemService.getItems().subscribe(
      (items) => this.items = [...items]
    );
  }

  logout() {
    localStorage.removeItem("access_token");
    this.router.navigate(["/login"]).then();
  }

  ngOnDestroy() {
    this.itemsSubscription.unsubscribe();
  }
}
