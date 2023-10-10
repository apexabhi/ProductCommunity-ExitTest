import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-showresult',
  templateUrl: './showresult.component.html',
  styleUrls: ['./showresult.component.sass']
})
export class ShowresultComponent implements OnInit {

  str!: any;
  size!:any;
  cuser!:any;
  isLogIn = false;
  allProducts!:any
  isValid:any
  isEmpty:any
  allProductsResult!:any
  productBrands:any = new Set();
  isNoProducts = false;
  filterProducts: any = [];
  brandFilterProducts:any = [];
  selectedBrand:any;
  priceFilterForm = new FormGroup({
    minPrice: new FormControl(''),
    maxPrice: new FormControl('')
  })
  constructor(private route:ActivatedRoute,private productApi:ProductService,private api:LoginService) { }

  ngOnInit(): void {
    this.isLogIn = this.api.isLoggedIn();
    this.api.loginStatusSubject.asObservable().subscribe((data)=>{
      this.isLogIn = this.api.isLoggedIn();
    });
    if(this.isLogIn===true){
      this.api.getUserDetails(this.api.getUser().username).subscribe(res=>{
        this.cuser=res;
        this.isLogIn=true;
        //console.log(res);
      })
    }
    this.route.params.subscribe(params=>{
      this.str = params['str'];
    })
    this.str=this.str.replace(/\s/g, "");
    console.log(this.str);
    const sarr=this.str.split(',');
    this.size=sarr.length;
    console.log(sarr);
    //console.log(this.str);
    if(this.size===1){
    this.productApi.getSearchResult(sarr[0]).subscribe(res=>{
      this.allProducts = res;
      this.allProductsResult = res;
      for(let item of this.allProductsResult) {
        this.productBrands.add(item.brand);
      }
      if(this.allProducts == '') {
        this.isNoProducts = true;
      }
      console.log(this.allProducts);
    },(err)=>{
      console.log("No products available")
      this.isNoProducts = true;
    })
  }
  else if(this.size===2){
    this.productApi.getSearchResultByTwo(sarr[0],sarr[1]).subscribe(res=>{
      this.allProducts = res;
      this.allProductsResult = res;
      for(let item of this.allProductsResult) {
        this.productBrands.add(item.brand);
      }
      if(this.allProducts == '') {
        this.isNoProducts = true;
      }
      console.log(this.allProducts);
    },(err)=>{
      console.log("No products available")
      this.isNoProducts = true;
    })
  }
  else if(this.size===3){
    this.productApi.getSearchResultByThree(sarr[0],sarr[1],sarr[2]).subscribe(res=>{
      this.allProducts = res;
      this.allProductsResult = res;
      for(let item of this.allProductsResult) {
        this.productBrands.add(item.brand);
      }
      if(this.allProducts == '') {
        this.isNoProducts = true;
      }
      console.log(this.allProducts);
    },(err)=>{
      console.log("No products available")
      this.isNoProducts = true;
    })
  }
    
  }
  onSelectedBrand(event:any) {
    this.isNoProducts = false;
    this.brandFilterProducts = [];
    this.selectedBrand = event;
    if(this.selectedBrand === "all" ) {
      this.allProducts = this.allProductsResult;
      return;
    }

    for (let item of this.allProductsResult) {
      if (item.brand.toLowerCase() === this.selectedBrand.toLowerCase()) {
        this.brandFilterProducts.push(item);
      }
    }

    this.allProducts = this.brandFilterProducts;
  }

  filterPrice() {
    this.filterProducts = [];
    this.isNoProducts = false;
    this.isValid = false;
    this.isEmpty = false;
    let minPrice: any = this.priceFilterForm.value.minPrice;
    let maxPrice: any = this.priceFilterForm.value.maxPrice;


    if (minPrice == '' && maxPrice != '') {
      minPrice = 0;
    } else if (minPrice != '' && (maxPrice == '' || maxPrice == null)) {
      maxPrice = Number.MAX_SAFE_INTEGER;
    } else if ((minPrice == '' || minPrice == null) && (maxPrice == '' || maxPrice == null)) {
      this.isEmpty=true;
      return;
    }


    if (minPrice > maxPrice) {
      this.isValid = true;
      return;
    }

    if (this.brandFilterProducts == '' || this.selectedBrand == "all") {
      for (let item of this.allProductsResult) {
        if (item.price >= minPrice && item.price <= maxPrice) {
          this.filterProducts.push(item);
        }
      }
    } else {
      for (let item of this.brandFilterProducts) {
        if (item.price >= minPrice && item.price <= maxPrice) {
          this.filterProducts.push(item);
        }
      }
    }

    this.allProducts = this.filterProducts;
    //this.allProductsResult=this.filterProducts;
    if (this.allProducts == '') {
      this.isNoProducts = true;
    }
  }



}
