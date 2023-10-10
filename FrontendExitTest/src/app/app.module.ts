import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsersModule } from './users/users.module';
import { LandingpageComponent } from './landingpage/landingpage.component';
import { authInterceptorProviders } from './services/auth.interceptor';
import { ProductsModule } from './products/products.module';
import { NavbarComponent } from './navbar/navbar.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { LoginGuard } from './services/login.guard';
@NgModule({
  declarations: [
    AppComponent,
    LandingpageComponent,
    NavbarComponent,
    PagenotfoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    UsersModule,
    ProductsModule
  ],
  providers: [authInterceptorProviders,LoginGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
