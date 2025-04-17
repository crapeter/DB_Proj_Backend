package CS._4.Project.Controllers;

import CS._4.Project.Models.Department;
import CS._4.Project.Services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @PostMapping("/add")
  public ResponseEntity<String> addDepartment(@RequestBody Department department) {
    return departmentService.addDepartment(department);
  }
}
