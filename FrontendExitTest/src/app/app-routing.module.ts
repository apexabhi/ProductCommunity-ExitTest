import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingpageComponent } from './landingpage/landingpage.component';
import { RegisterComponent } from './users/register/register.component';
import { LoginComponent } from './users/login/login.component';
import { SearchproductsComponent } from './products/searchproducts/searchproducts.component';
import { ShowresultComponent } from './products/showresult/showresult.component';
import { ProductdetailsComponent } from './products/productdetails/productdetails.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { LoginGuard } from './services/login.guard';

const routes: Routes = [
  {
    path:'',redirectTo:'land',pathMatch:'full'
  },
  {
    path:'land',component:LandingpageComponent
  },
  {
    path:'register',component:RegisterComponent
  },
  {
    path:'login',component:LoginComponent
  },
  {
    path:'showallproducts',component:SearchproductsComponent
  },
  {
    path:'result/:str',component:ShowresultComponent,canActivate:[LoginGuard]
  },
  {
    path:'productdetail/:str',component:ProductdetailsComponent,canActivate:[LoginGuard]
  },
  {
    path:'**',component:PagenotfoundComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
