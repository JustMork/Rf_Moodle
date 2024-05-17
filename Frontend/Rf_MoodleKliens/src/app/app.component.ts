import { Component, OnInit } from '@angular/core';
import { WebsocketService } from './services/websocket.service';
import { MoodleEvent } from './models/moodleEvent';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{

  constructor(private websocketService:WebsocketService){
    this.websocketService.connect();

    this.websocketService.event$.subscribe({
      next: (event:MoodleEvent|null) => {
        if(event!=null)
        alert(`Új esemény:\n${event?.name}\n${event?.description}`);  
      }
    })
  }

  title = 'Rf_MoodleKliens';

}