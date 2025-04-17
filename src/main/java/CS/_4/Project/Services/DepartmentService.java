package CS._4.Project.Services;

import CS._4.Project.Models.Department;
import CS._4.Project.Repositories.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
  private final DepartmentRepository departmentRepo;

  public DepartmentService(DepartmentRepository departmentRepo) {
    this.departmentRepo = departmentRepo;
  }

  public ResponseEntity<String> addDepartment(Department department) {
    if (department.getName() == null || department.getName().isEmpty()) {
      return ResponseEntity.badRequest().body("Department name is required");
    }

    try {
      departmentRepo.save(department);
      return ResponseEntity.ok("Department added successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error saving department: " + e.getMessage());
    }
  }
}
