import { Component, OnInit, Injectable } from '@angular/core';
import { PhotoService } from '../photo.service';
import { Photo } from '../photo';
import { Page } from '../page';
import { Sort } from '../sort';
import { Criteria } from '../criteria';
import { DatePipe } from '@angular/common';
import {NgbModal, ModalDismissReasons, NgbDateAdapter, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

/**
 * Example of a Native Date adapter
 */
@Injectable()
export class NgbDateNativeAdapter extends NgbDateAdapter<string> {

    fromModel(date: string): NgbDateStruct {
        var mydate = new Date(Date.parse(date));
        return (mydate && mydate.getFullYear) ? { year: mydate.getFullYear(), month: mydate.getMonth() + 1, day: mydate.getDate() } : null;
    }

    toModel(date: NgbDateStruct): string {
        var dt = date ? date.year + '-' + date.month + '-' + date.day : null;
        return dt;
    }
}

@Component({
    selector: 'app-photos',
    templateUrl: './photos.component.html',
    styleUrls: ['./photos.component.css'],
    providers: [{provide: NgbDateAdapter, useClass: NgbDateNativeAdapter}]
})
export class PhotosComponent implements OnInit {

    photos: Photo[];

    cities = [
        { id: "Amiens", name: "Amiens" },
        { id: "Paris", name: "Paris" },
        { id: "Vannes", name: "Vannes" }
    ];

    criteria: Criteria = new Criteria();

    columns = [
        { name: 'Id', prop: 'id' },
        { name: 'File name', prop: 'filename' },
        { name: 'Location', prop: 'location' },
        { name: 'User', prop: 'username' },
        { name: 'Date', prop: 'datetime', pipe: new DateTimePipe('en-US') }
    ];

    allColumns = [
        { name: 'Id', prop: 'id' },
        { name: 'File name', prop: 'filename' },
        { name: 'Location', prop: 'location' },
        { name: 'User', prop: 'username' },
        { name: 'Date', prop: 'datetime', pipe: new DateTimePipe('en-US') }
    ];

    page = new Page();

    constructor(private photoService: PhotoService, private modalService: NgbModal, private router: Router) {
        this.page.pageNumber = 0;
        this.page.size = 8;
        this.criteria.sort = 'id';
        this.criteria.dir = 'asc';
        this.criteria.pageNumber = 0;
        this.criteria.size = 8;
    }

    ngOnInit() {
        this.getPhotos({ offset: 0 });
    }

    getPhotos(pageInfo): void {
        this.criteria.pageNumber = pageInfo.offset;
        this.photoService.getPhotos(this.criteria)
            .subscribe(page => {
                this.photos = page.content;
                this.page = page;
                this.page.pageNumber = pageInfo.offset;
            });
    }

    sortPhotos(event): void {
        this.criteria.sort = event.sorts[0].prop;
        this.criteria.dir = event.sorts[0].dir;
        this.criteria.pageNumber = 0;
        this.photoService.getPhotos(this.criteria)
            .subscribe(page => {
                this.photos = page.content
                this.page = page;
            });
    }

    onSubmit(): void {
        this.criteria.pageNumber = 0;
        this.photoService.getPhotos(this.criteria)
            .subscribe(page => {
                this.photos = page.content
                this.page = page;
                this.page.pageNumber = 0;
            });
    }

    toggle(col) {
        const isChecked = this.isChecked(col);

        if (isChecked) {
            this.columns = this.columns.filter(c => {
                return c.name !== col.name;
            });
        } else {
            this.columns = [...this.columns, col];
        }
    }

    isChecked(col) {
        return this.columns.find(c => {
            return c.name === col.name;
        });
    }

    open(content) {
        this.modalService.open(content, { centered: true}).result.then((result) => {

        }, (reason) => {

        });
    }
    
    cancelFilter(){
        this.criteria.startdate = null;
        this.criteria.enddate = null;
        this.criteria.location = null;
    }

    onSelect({ selected }) {
        console.log('Select Event');
    }

    onActivate(event) {
        if (event.type === 'dblclick') {
            this.router.navigate(['/detailphotos']);
            return true;
        }
    }



}

class DateTimePipe extends DatePipe {
    public transform(value): any {
        return super.transform(value, 'dd/MM/y HH:m:s');
    }
}
