import {Component} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import Site from '../model/Site';
import {Observable} from "rxjs";
import {Url} from "../model/Url";
import {FormsModule, NgForm} from "@angular/forms";
import SiteResponse from "../model/SiteResponse";
import {KeyValuePipe, NgForOf} from "@angular/common";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    imports: [
        FormsModule,
        KeyValuePipe,
        NgForOf
    ],
    standalone: true
})
export class AppComponent {

    constructor(
        private http: HttpClient) {
    }

    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    site: Site = {}

    submit(form: NgForm) {
        this.getSiteInfo({url: form.value.url})
            .subscribe(response => {
                this.site = response.entity
            });
    }

    getSiteInfo(url: Url): Observable<SiteResponse> {
        return this.http.post<SiteResponse>('http://localhost:8080/urlContent', url, this.httpOptions)
    }

    protected readonly JSON = JSON;
}
