import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserRegistrationData } from 'src/app/model/user-registration.model';
import { ConfirmedValidator } from 'src/app/services/confirmed-validator';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.sass']
})
export class RegisterComponent implements OnInit {

  successAlert = false;
  failureAlert = false;
  pwd2 = new FormControl();
  signupForm!: FormGroup
  userRegistrationModelObj: UserRegistrationData = new UserRegistrationData;
  constructor(private formBuilder: FormBuilder, private api: UserService,private router:Router) { }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      firstname: ['', [Validators.required, Validators.pattern('^[a-zA-Z]+$')]],
      lastname: ['', [Validators.required, Validators.pattern('^[a-zA-Z.]+$')]],
      password: ['',[Validators.required, Validators.pattern(
            '(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-zd$@$!%*?&].{3,}'
          ), Validators.minLength(4)]],
      password2: ['', [Validators.required]]
    },
    {
      validators: ConfirmedValidator('password', 'password2'),
    })
}

get func() {
  return this.signupForm.controls;
}

signUp() {
  this.userRegistrationModelObj.email = this.signupForm.value.email;
  this.userRegistrationModelObj.firstname = this.signupForm.value.firstname;
  this.userRegistrationModelObj.lastname = this.signupForm.value.lastname;
  this.userRegistrationModelObj.password = this.signupForm.value.password;

  this.api.postUser(this.userRegistrationModelObj).subscribe(res => {
    console.log(res);
    this.successAlert = true;
    setTimeout(() => {
      this.successAlert = false;
    }, 1000);
    this.signupForm.reset();
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 2000);
    
  },
    err => {
      this.failureAlert = true;
      setTimeout(() => {
        this.failureAlert = false;
      }, 2000);
    })
  }
  closeSuccessAlert() {
    this.successAlert = false;
  }

  closeFailureAlert() {
    this.failureAlert = false;
  }


}


