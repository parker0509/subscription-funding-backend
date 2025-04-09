package mall.shopping.mall.dto;

import lombok.Getter;
import lombok.Setter;
import mall.shopping.mall.entity.Project;

import java.math.BigDecimal;

@Getter
@Setter
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal goalAmount;
    private BigDecimal raisedAmount;
    private String imageUrl;
    private String creatorEmail;

    // ✅ 이 static 메서드가 핵심!
    public static ProjectResponseDto fromEntity(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setGoalAmount(project.getGoalAmount());
        dto.setRaisedAmount(project.getRaisedAmount());
        dto.setImageUrl(project.getImageUrl());

        // Lazy 로딩 조심! 이 라인에서 NullPointerException 날 수 있음.
        if (project.getCreator() != null) {
            dto.setCreatorEmail(project.getCreator().getEmail());
        }

        return dto;
    }
}