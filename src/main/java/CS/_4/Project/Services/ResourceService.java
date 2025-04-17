package CS._4.Project.Services;

import CS._4.Project.Models.Resource;
import CS._4.Project.Repositories.ResourceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
  private final ResourceRepository resourceRepo;

  public ResourceService(ResourceRepository resourceRepo) {
    this.resourceRepo = resourceRepo;
  }

  public ResponseEntity<String> getResourceByType(String rType) {
    Resource resource = resourceRepo.getResourceByRType(rType);
    if (resource != null) {
      return ResponseEntity.ok(resource.toString());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
