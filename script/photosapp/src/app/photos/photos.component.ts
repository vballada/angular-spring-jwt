import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../photo.service';
import { Photo } from '../photo';
import { Page } from '../page';
import { Sort } from '../sort';
import { DatePipe } from '@angular/common';

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
        { name: 'User', prop: 'username' },
        { name: 'Date', prop: 'datetime', pipe: new DateTimePipe('en-US') }
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
                this.photos = page.content;
                this.page = page;
                this.page.pageNumber = pageInfo.offset;
                console.log(this.page);
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

class DateTimePipe extends DatePipe {
  public transform(value): any {
    return super.transform(value, 'dd/MM/y HH:m:s');
  }
}
