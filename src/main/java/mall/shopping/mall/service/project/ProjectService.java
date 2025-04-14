package mall.shopping.mall.service.project;

import lombok.RequiredArgsConstructor;
import mall.shopping.mall.dto.ProjectRequestDto;
import mall.shopping.mall.dto.ProjectResponseDto;
import mall.shopping.mall.entity.Project;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.repository.ProjectRepository;
import mall.shopping.mall.repository.UserRepository;
import mall.shopping.mall.service.user.UserService;
import mall.shopping.mall.util.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(ProjectRequestDto projectRequestDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Project project = new Project();
        project.setTitle(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setGoalAmount(projectRequestDto.getGoalAmount());
        project.setImageUrl(projectRequestDto.getImageUrl());
        project.setCreatorName(user); // 로그인한 사용자 지정
        project.setTags(projectRequestDto.getTags());  // 변환된 리스트 저장 // 리스트로 변환하여 저장
        return projectRepository.save(project);
    }

    @Transactional
    public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setTitle(updatedProject.getTitle());
            project.setDescription(updatedProject.getDescription());
            project.setGoalAmount(updatedProject.getGoalAmount());
            project.setEndDate(updatedProject.getEndDate());
            project.setTags(updatedProject.getTags());
            return projectRepository.save(project);
        }).orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
