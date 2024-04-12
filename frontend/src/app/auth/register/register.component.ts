import {Component} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthService} from "../../core/service/auth.service";
import {UserModel} from "../../shared/user.model";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  profileForm = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(' '),
    password: new FormControl(''),
  });

  constructor(private authService: AuthService) {
  }

  onSubmit() {
    console.table(this.profileForm.value);
    const user: UserModel = {
      username: this.profileForm.value.username!,
      email: this.profileForm.value.email!,
      password: this.profileForm.value.password!,
      roles: [
        "CUSTOMER"
      ]
    }
    this.authService.register(user).subscribe();
  }
}
