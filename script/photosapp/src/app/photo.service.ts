import { Injectable } from '@angular/core';
import { Photo } from './photo';
import { Page } from './page';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class PhotoService {

    private photosUrl = 'api/photos';
    constructor(private http: HttpClient) { }

    getPhotos(page, sort): Observable<Page> {
        return this.http.get<Page>(this.photosUrl+'?pageNumber='+page.pageNumber+'&size='+page.size+'&sort='+sort.prop+'&dir='+sort.dir);
    }
}
