import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main/main.component';
import { AuthGuard } from './auth.guard';
import { LoginComponent } from './components/login/login.component';
import { CourseComponent } from './components/course/course.component';
import { MycoursesComponent } from './components/mycourses/mycourses.component';
import { EventsComponent } from './components/events/events.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'course/:courseId', component: CourseComponent},
  { path: 'course/:courseId/events', component: EventsComponent},
  { path: 'mycourses', component: MycoursesComponent},
  { path: '', component: MainComponent, pathMatch: 'full', canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
