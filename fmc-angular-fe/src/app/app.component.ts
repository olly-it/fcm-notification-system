import { Component, OnInit } from '@angular/core';
import { environment } from "../environments/environment";
import { getMessaging, getToken, onMessage } from "firebase/messaging";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'fmc-angular-fe';
  message:any = null;
  token:any = null;
  constructor() {}
  ngOnInit(): void {
    this.requestPermission();
    this.listen();
  }
  requestPermission() {
    const messaging = getMessaging();
    getToken(messaging, 
     { vapidKey: environment.firebase.vapidKey}).then(
       (currentToken) => {
         if (currentToken) {
           console.log("Hurraaa!!! we got the token.....");
           console.log(currentToken);
           this.token = currentToken;
         } else {
           console.log('No registration token available. Request permission to generate one.');
         }
     }).catch((err) => {
        console.log('An error occurred while retrieving token. ', err);
    });
  }
  listen() {
    const messaging = getMessaging();
    onMessage(messaging, (payload) => {
      console.log('Message received. ', payload);
      this.message=payload;
    });
  }

  jsonToString (value:any) {
    return JSON.stringify(value,null,2);
  }
  clipboardToken() {
    navigator.clipboard.writeText(this.token);
  }
}
