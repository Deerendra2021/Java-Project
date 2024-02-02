import { Component } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../services/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'
})
export class CreateEmployeeComponent {

  employee: Employee = new Employee();

  constructor(private employeeService: EmployeeService,
              private router: Router) { }

  ngOnInit(): void {

  }

  onSubmit() {
    this.postEmployee();
  }

  postEmployee() {
    this.employeeService.createEmployee(this.employee).subscribe(data => {
      this.goToEmployeeList();
    },
    error => console.log(error));
  }

  goToEmployeeList() {
    this.router.navigate(['/employees']);
  }

}
