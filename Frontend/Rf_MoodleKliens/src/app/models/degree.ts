import { Course } from "./course";

export class Degree {

    id: number;
    name : string;
    availableCourses : Set<Course>
}
