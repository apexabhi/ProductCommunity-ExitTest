import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-productdetails',
  templateUrl: './productdetails.component.html',
  styleUrls: ['./productdetails.component.sass']
})
export class ProductdetailsComponent implements OnInit {

  str!:any;
  detail:any;
  cuser:any;
  isLogIn=false;
  isDeliverable:any;
  expectedtime:any;
  result:any={};
  checkForm = new FormGroup({
    dtext: new FormControl(),
  });

  constructor(private route:ActivatedRoute, private router:Router,private api:ProductService, private lapi:LoginService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=>{
      this.str = params['str'];
    })
    this.lapi.getUserDetails(this.lapi.getUser().username).subscribe(res=>{
      this.cuser=res;
      this.isLogIn=true;
      //console.log(res);
    })
    this.api.getProductDetails(this.str).subscribe(res=>{
      this.detail=res;
      console.log(this.detail);
    })
  }
  setTimeDate() {
    if (this.result == null) {
      this.isDeliverable = 2;
      return;
    }
    this.expectedtime = new Date();
    this.expectedtime.setDate(this.expectedtime.getDate() + this.result.days);
    this.expectedtime = this.expectedtime.toISOString().slice(0,10);
    console.log(this.expectedtime);
    if (this.result.days== null) {
      this.isDeliverable = 3;
      return;
    }
    this.isDeliverable = 1;
  }

  checkDelivery(productcode: any) {
    if (this.checkForm.value.dtext == null) {
      this.isDeliverable = 3;
      return;
    }
    this.api.checkDelivery(productcode,this.checkForm.value.dtext).subscribe(res=>{
      this.result= res;
      console.log(res);
      this.setTimeDate();
    },err=>{
      this.isDeliverable = 2;
    })
  }


}
