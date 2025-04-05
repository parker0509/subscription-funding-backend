package mall.shopping.mall.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mall.shopping.mall.entity.User;

import java.math.BigDecimal;

@Getter
@Setter
public class ProjectRequestDto {
    @NotBlank(message = "프로젝트 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "설명은 필수입니다.")
    private String description;

    @NotNull(message = "목표 금액을 입력해주세요.")
    private BigDecimal goalAmount;

    private String imageUrl; // 이미지 URL은 선택 사항

    @NotNull(message = "프로젝트 생성자는 필수입니다.")
    private User creatorId; // 유저 ID 추가
}
