package mall.shopping.mall.service.project;

import lombok.RequiredArgsConstructor;
import mall.shopping.mall.dto.ProjectRequestDto;
import mall.shopping.mall.entity.Project;
import mall.shopping.mall.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public Project createProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setGoalAmount(projectRequestDto.getGoalAmount());
        project.setImageUrl(projectRequestDto.getImageUrl());
        project.setCreator(projectRequestDto.getCreatorId()); // 유저 설정

        return projectRepository.save(project);
    }

    @Transactional
    public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setGoalAmount(updatedProject.getGoalAmount());
            project.setEndDate(updatedProject.getEndDate());
            return projectRepository.save(project);
        }).orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다."));
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
