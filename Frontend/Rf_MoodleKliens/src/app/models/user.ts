import { Course } from "./course";
import { Degree } from "./degree";

export class User {

    username : string;
    name : string;
    password : string;
    degree : Degree;
    admin : boolean;
    //courses : Set<Course>;

}
