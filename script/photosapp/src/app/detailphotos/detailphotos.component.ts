import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../photo.service';
import { Photo } from '../photo';

@Component({
    selector: 'app-detailphotos',
    templateUrl: './detailphotos.component.html',
    styleUrls: ['./detailphotos.component.css']
})
export class DetailphotosComponent implements OnInit {

    photos: Photo[];

    constructor(private photoService: PhotoService) { }

    ngOnInit() {
        this.getAllPhotos();
    }

    getAllPhotos(): void {
        this.photoService.getAllPhotos()
            .subscribe(photos => {
                this.photos = photos;
            });
    }

}
