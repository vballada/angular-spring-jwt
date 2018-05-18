import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { PhotosComponent } from './photos/photos.component';
import { PhotoService } from './photo.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './/app-routing.module';
import { DetailphotosComponent } from './detailphotos/detailphotos.component';

@NgModule({
    declarations: [
        AppComponent,
        PhotosComponent,
        DetailphotosComponent
    ],
    imports: [
        NgxDatatableModule,
        BrowserModule,
        FormsModule,
        HttpClientModule,
        NgbModule.forRoot(),
        AppRoutingModule
    ],
    providers: [
        PhotoService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
