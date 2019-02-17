import { Component } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'String Cloud ECS Example App';
  model:Model = new Model();
  greeting = null;

  constructor(private http: HttpClient) { }

  submit() {
    let url = "http://localhost:9090/v1/hello/greeting?name=" + this.model.name;
    if (this.model.location != null) {
      url += "&location="+this.model.location;
    }
    const httpHeaders = new HttpHeaders({
        'Access-Control-Allow-Origin':  '*'
      });
    this.http.get(url, { responseType: 'text', headers: httpHeaders })
      .subscribe(data => {
        console.log(data);
        this.greeting = data;
      });
  }
}

class Model {
  name;
  location;
}
