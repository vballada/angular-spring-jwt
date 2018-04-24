import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../photo.service';
import { Photo } from '../photo';
import { Page } from '../page';

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

    page = new Page();

    constructor(private photoService: PhotoService) {
        this.page.pageNumber = 0;
        this.page.size = 5;
    }

    ngOnInit() {
        this.getPhotos({ offset: 0 });
    }

    getPhotos(pageInfo): void {
        this.page.pageNumber = pageInfo.offset;
        this.photoService.getPhotos(this.page)
            .subscribe(page => {
                this.photos = page.content
                this.page = page;
            });
    }



}
