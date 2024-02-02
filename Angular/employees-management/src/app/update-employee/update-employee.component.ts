import { Component } from '@angular/core';
import { Employee } from '../model/employee';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent {

  employee: Employee = new Employee();
  id?: number;

  constructor(private employeeService: EmployeeService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    
    this.employeeService.getEmployeeById(this.id).subscribe(data => {
      this.employee = data;
    },
      error => console.log(error));
  }

  onSubmit() {
    this.updateEmployee();
  }

 updateEmployee() {
  this.employeeService.updateEmployee(this.id, this.employee).subscribe(data => {
    this.navigateToEmployeeList();

  }, error => console.log(error)); 
 }

 navigateToEmployeeList() {
  this.router.navigate(['/employees']);
 }

}
