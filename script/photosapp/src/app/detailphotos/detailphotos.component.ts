import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-detailphotos',
    templateUrl: './detailphotos.component.html',
    styleUrls: ['./detailphotos.component.css']
})
export class DetailphotosComponent implements OnInit {

    images = ['http://localhost:8080/assets/img/photo_1.jpg', 'http://localhost:8080/assets/img/photo_2.jpg', 'http://localhost:8080/assets/img/photo_3.jpg'];

    constructor() { }

    ngOnInit() {
    }

}
