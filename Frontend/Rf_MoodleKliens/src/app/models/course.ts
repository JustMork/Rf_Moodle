import { Degree } from "./degree";
import { User } from "./user";

export class Course {

    id : number;
    code : string;
    name : string;
    credit : number;
    approvedDegrees : Set<Degree>
    users : Set<User>
    event : Set<Event>    
    
}
