import { Component } from '@angular/core';
import { Employee } from '../model/employee';
import { EmployeeService } from '../services/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent {

  employees !: Employee[];

  constructor(private employeeService: EmployeeService,
              private router: Router) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  private getEmployees() {
    this.employeeService.getAllEmployees().subscribe(data => {
      this.employees = data;
    });
  }

  updateEmployee(id?: number) {
    this.router.navigate(['/update-employee', id]);
  }

  deleteEmployee(id?: number) {
    this.employeeService.deleteEmployee(id).subscribe(data => {
      this.getEmployees();
    });
  }
}
