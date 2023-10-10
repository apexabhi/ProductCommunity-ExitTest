import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-landingpage',
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.sass']
})
export class LandingpageComponent implements OnInit {
  logoutAlert=false;

  constructor(private router:Router, private api:LoginService) {

   }

  ngOnInit(): void {
    //window.location.reload();
    if(this.api.isLoggedIn()){
      this.router.navigate(['/showallproducts'])
    }
    else{
      setTimeout(()=>{this.logoutAlert=true},200);
      setTimeout(()=>{this.logoutAlert=false},1000);
    }
  }
  closeLogoutAlert() {
    this.logoutAlert = false;
  }

}
