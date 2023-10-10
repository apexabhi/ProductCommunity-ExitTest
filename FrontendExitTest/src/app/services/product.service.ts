import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }
  public getProducts() {
    return this.http.get(`http://localhost:8090/product`);
  }
  public getSearchResult(str:string) {
    return this.http.get(`http://localhost:8090/product/search/1/`+str);
  }
  public getSearchResultByTwo(str1:string,str2:string){
    return this.http.get(`http://localhost:8090/product/search/2/`+str1+`/`+str2);
  }
  public getSearchResultByThree(str1:string,str2:string,str3:string){
    return this.http.get(`http://localhost:8090/product/search/3/`+str1+`/`+str2+`/`+str3);
  }
  public getProductDetails(str:string){
    return this.http.get(`http://localhost:8090/product/detail/`+str)
  }
  public checkDelivery(productcode:any, pincode:any) {
    return this.http.get("http://localhost:8090/delivery/"+productcode+"/"+pincode);
  }
}
