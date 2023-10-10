import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.sass']
})
export class NavbarComponent implements OnInit {

  isLogIn=false
  temp=this.api;
  constructor(private api:LoginService, private router:Router) {
   }

  ngOnInit(): void {
    this.isLogIn = this.api.isLoggedIn();
    this.api.loginStatusSubject.asObservable().subscribe((data)=>{
      this.isLogIn = this.api.isLoggedIn();
    });
  }

  public logout() {
    this.api.logout();
    //alert("log out done")
    this.router.navigate(['']);
    setTimeout(()=>{
      window.location.reload();
    }, 5);
  }

}
