import { Injectable } from '@angular/core';
import { Photo } from './photo';
import { Page } from './page';
import { Criteria } from './criteria';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class PhotoService {

    private photosUrl = 'api/photos';
    private criteria: Criteria = new Criteria();
    
    constructor(private http: HttpClient) { }
    
    getPhotos(criteria): Observable<Page> {
        return this.http.post<Page>(this.photosUrl, criteria);
    }
}
