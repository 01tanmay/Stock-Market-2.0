import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  //imgSrc='assets/images/stockmarketlogo.PNG'
  imgSrc='assets/images/sm1.cms'
  imgAlt='E-Stockmarket'
  constructor(public haService: AuthenticationService) { }

  ngOnInit(): void {
  }

}
