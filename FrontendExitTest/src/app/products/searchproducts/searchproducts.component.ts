import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-showallproducts',
  templateUrl: './searchproducts.component.html',
  styleUrls: ['./searchproducts.component.sass']
})
export class SearchproductsComponent implements OnInit {

  isLogIn = false;
  isNotSearch=true;
  cuser!:any;
  productBrands: any = new Set();
  isNoProducts = false;
  isEmpty = false;
  isValid = false;
  allProducts: any = [];
  allProductsResult: any = [];
  filterProducts: any = [];
  brandFilterProducts: any = [];
  selectedBrand: any;
  searchForm = new FormGroup({
    stext: new FormControl()
  });
  priceFilterForm = new FormGroup({
    minPrice: new FormControl(''),
    maxPrice: new FormControl('')
  })
  constructor(public api: LoginService, public productApi: ProductService, public router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.isLogIn = this.api.isLoggedIn();
    this.api.loginStatusSubject.asObservable().subscribe((data) => {
      this.isLogIn = this.api.isLoggedIn();
    });
    if(this.isLogIn===true){
      this.api.getUserDetails(this.api.getUser().username).subscribe(res=>{
        this.cuser=res;
        this.isLogIn=true;
        //console.log(res);
      })
    }
    this.productApi.getProducts().subscribe(res => {
      this.allProducts = res;
      this.allProductsResult = res;
      for (let item of this.allProductsResult) {
        this.productBrands.add(item.brand);
      }
      if (this.allProducts == '') {
        this.isNoProducts = true;
      }
    },(err)=>{
      console.log("No products available")
      this.isNoProducts = true;
    })
  }
  get func() {
    return this.searchForm.controls;
  }
  searchProduct() {
    this.router.navigate(["result/" + this.searchForm.value.stext]);
    this.isNotSearch=false;
  }
  onSelectedBrand(event: any) {
    this.isNoProducts = false;
    this.brandFilterProducts = [];
    this.selectedBrand = event;
    if (this.selectedBrand === "all") {
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
