import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PhotosComponent }      from './photos/photos.component';

const routes: Routes = [
  { path: '', component: PhotosComponent },
  { path: 'lstphotos', component: PhotosComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
})
export class AppRoutingModule {}