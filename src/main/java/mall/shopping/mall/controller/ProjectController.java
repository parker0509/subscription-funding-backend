package mall.shopping.mall.controller;

import lombok.RequiredArgsConstructor;
import mall.shopping.mall.dto.ProjectRequestDto;
import mall.shopping.mall.dto.ProjectResponseDto;
import mall.shopping.mall.entity.Project;
import mall.shopping.mall.service.project.ProjectService;
import mall.shopping.mall.util.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // React 연동을 위해 CORS 설정
public class ProjectController {

    private final ProjectService projectService;



    //JWT 검증 필요 AUTH () 분할 필요
    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable("id") Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project
                .map(ProjectResponseDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequestDto dto,
                                                 @AuthenticationPrincipal CustomUserDetails userDetails) {
        Project savedProject = projectService.createProject(dto, userDetails.getUsername());
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
