import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../photo.service';
import { Photo } from '../photo';
import { Page } from '../page';
import { Sort } from '../sort';

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
    
    sort = new Sort();

    constructor(private photoService: PhotoService) {
        this.page.pageNumber = 0;
        this.page.size = 5;
        this.sort.prop = 'id';
        this.sort.dir = 'asc';
    }

    ngOnInit() {
        this.getPhotos({ offset: 0 });
    }

    getPhotos(pageInfo): void {
        this.page.pageNumber = pageInfo.offset;
        this.photoService.getPhotos(this.page, this.sort)
            .subscribe(page => {
                this.photos = page.content
                this.page = page;
            });
    }
    
    sortPhotos(event): void {
        this.sort.prop = event.sorts[0].prop;
        this.sort.dir = event.sorts[0].dir;
        this.page.pageNumber = 0;
        this.photoService.getPhotos(this.page, this.sort)
            .subscribe(page => {
                this.photos = page.content
                this.page = page;
            });
    }



}
