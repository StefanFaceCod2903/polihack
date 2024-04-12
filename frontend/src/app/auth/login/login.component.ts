import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../../core/service/auth.service";
import {UserModel} from "../../shared/user.model";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(' '),
    password: new FormControl(''),
  });

  constructor(private authService: AuthService, private route: ActivatedRoute, private router: Router) {
  }

  onSubmit() {
    const user: UserModel = {
      username: this.loginForm.value.username!,
      password: this.loginForm.value.password!,
    }
    this.authService.login(user).subscribe(({token}) => {
      if(token) {
        localStorage.setItem("access_token", token)
        this.router.navigate(['/items'], {relativeTo: this.route}).then();
      }
    });
  }
}
