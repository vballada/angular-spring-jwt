import { Injectable } from '@angular/core';
import { Photo } from './photo';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class PhotoService {

    private photosUrl = 'api/photos';
    constructor(private http: HttpClient) { }

    getPhotos(): Observable<Photo[]> {
        return this.http.get<Photo[]>(this.photosUrl)
    }
}
