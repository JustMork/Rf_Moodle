import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import { MoodleEvent } from '../models/moodleEvent';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private client: Client;
  private eventSubject = new BehaviorSubject<MoodleEvent | null>(null);
  event$ = this.eventSubject.asObservable();

  constructor() {
    this.client = new Client({
      brokerURL: 'ws://localhost:3000/events',
      reconnectDelay: 1000*60,
      debug: (str) => {
        console.log(str);
      }
    });

    this.client.onConnect = (frame) => {
      console.log('Connected: ' + frame);
      this.client.subscribe('/topic/createdEvent', (message: Message) => {
        const event: MoodleEvent = JSON.parse(message.body);
        this.eventSubject.next(event);
      });
    };

    this.client.onWebSocketError = (error) => {
      
      console.error('Error with websocket', error);
    };

    this.client.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };
  }

  connect() {
    console.log(this.client.connectHeaders);
    this.client.activate();
  }

  disconnect() {
    this.client.deactivate();
    this.eventSubject.next(null);
    console.log("Disconnected");
  }
}