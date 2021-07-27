import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './guards/admin.guard';
import { NormalGuard } from './guards/normal.guard';
import { AddCategoryComponent } from './pages/admin/add-category/add-category.component';
import { AddQuestionComponent } from './pages/admin/add-question/add-question.component';
import { AddQuizComponent } from './pages/admin/add-quiz/add-quiz.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { UpdateCategoryComponent } from './pages/admin/update-category/update-category.component';
import { UpdateQuizComponent } from './pages/admin/update-quiz/update-quiz.component';
import { ViewCategoriesComponent } from './pages/admin/view-categories/view-categories.component';
import { ViewQuestionsComponent } from './pages/admin/view-questions/view-questions.component';
import { ViewQuizzesComponent } from './pages/admin/view-quizzes/view-quizzes.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { SignupComponent } from './pages/signup/signup.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'signup', component: SignupComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent, pathMatch: 'full' },
  {
    path: 'admin', component: DashboardComponent, canActivate: [AdminGuard],
    children: [
      { path: '', component: WelcomeComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'categories', component: ViewCategoriesComponent },
      { path: 'add-category', component: AddCategoryComponent },
      { path: 'edit-category/:cid', component: UpdateCategoryComponent },
      { path: 'view-quizzes', component: ViewQuizzesComponent },
      { path: 'add-quiz', component: AddQuizComponent },
      { path: 'edit-quiz/:qid', component: UpdateQuizComponent },
      { path: 'view-questions/:qid/:title', component: ViewQuestionsComponent },
      { path: 'add-question/:qid', component: AddQuestionComponent },
    ]
  },
  { path: 'user-dashboard', component: UserDashboardComponent, pathMatch: 'full', canActivate: [NormalGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
