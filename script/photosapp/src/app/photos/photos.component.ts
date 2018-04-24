import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../photo.service';
import { Photo } from '../photo';

@Component({
    selector: 'app-photos',
    templateUrl: './photos.component.html',
    styleUrls: ['./photos.component.css']
})
export class PhotosComponent implements OnInit {

    photos: Photo[];

    columns = [
        { prop: 'id' },
        { name: 'File name', prop: 'filename' },
        { name: 'Location', prop: 'location' },
        { name: 'User', prop: 'username' }
    ];

    constructor(private photoService: PhotoService) { }

    ngOnInit() {
        this.getPhotos();
    }

    getPhotos(): void {
        this.photoService.getPhotos()
            .subscribe(photos => this.photos = photos);
    }

}
