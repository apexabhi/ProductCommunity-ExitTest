import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserLoginData } from 'src/app/model/user-login.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  loginForm!:FormGroup
  userObj: UserLoginData = new UserLoginData;
  failureAlert = false;
  successAlert=false;
  constructor(private formBuilder: FormBuilder, private router: Router, private api:LoginService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      userid: [''],
      pwd: ['']

    })
    if(this.api.isLoggedIn()){
      this.router.navigate(['/showallproducts'])
    }
  }
  logIn(){
    this.userObj.username = this.loginForm.value.userid;
    this.userObj.password = this.loginForm.value.pwd;

    this.api.generateToken(this.userObj).subscribe(
      (data: any) => {
        console.log(data);

        this.api.loginUser(data.token);

        this.api.getCurrentUser().subscribe(
          (user: any) => {
            this.api.setUser(user);
            console.log(user);
            this.loginForm.reset();
            setTimeout(()=>{this.successAlert=true},500);
            setTimeout(()=>{this.router.navigate(['/showallproducts'])},1000);
          }
        );
      }, (err) => {
        console.log('Error');
        console.log(err);
        this.loginForm.reset();
        setTimeout(()=>{this.failureAlert=true},500);
        setTimeout(()=>{this.failureAlert=false},2000);
      }
    );
    
  }

  closeFailureAlert() {
    this.failureAlert = false;
  }
  closeSuccessAlert() {
    this.successAlert = false;
  }

}
