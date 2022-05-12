import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public user = {
    username : '',
    password:'',
    firstName: '',
    lastName:'',
    email:'',
    phone:'',
  }
   hide=true;
  constructor(private userService : UserService , private snack : MatSnackBar ) { }

  ngOnInit(): void {
  }
  formSubmit(){
     console.log('success');
     if(this.user.username == '' || this.user.username == null){
      // alert('Username is required!!');
      this.snack.open('Username is required!!','',{
        duration:3000,
      });
       return;
     }
    
     //addUser :service
     this.userService.addUser(this.user).subscribe(
      (data : any)=>{
        //success
        console.log(data);
       //alert('success');
      Swal.fire('Successfully done !!','User is is '+data.id,'success');
        
      },
      (error)=>{
        //error
        console.log(error);
       // alert('Something went wrong');
       this.snack.open('Something went wrong !!','',{
         duration:3000,
       });
      }
    );
  }

}
