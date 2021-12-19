import { Component } from '@angular/core';
import { categoriesUrl, expensesUrl, homeUrl, incomesUrl } from './models/links';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  home = homeUrl;
  categories = categoriesUrl;
  expenses = expensesUrl;
  incomes: string = incomesUrl;
  title = 'angular-ui';
}
