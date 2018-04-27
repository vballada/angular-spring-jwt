/**
 * An object used to get page information from the server
 */
export class Criteria {
    //The number of elements in the page
    location: string;
    pageNumber: number;
    startdate: Date;
    enddate: Date;
    size: number;
    sort: string;
    dir: string;
}