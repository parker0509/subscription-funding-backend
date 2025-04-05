package mall.shopping.mall.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mall.shopping.mall.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name; // 프로젝트 이름

    @NotBlank
    private String description; // 프로젝트 설명

    private String ImageUrl;

    @Column(nullable = false)
    @NotBlank
    private BigDecimal goalAmount; // 목표 금액

    private BigDecimal raisedAmount = BigDecimal.ZERO; // 현재 모금된 금액

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator; // 프로젝트 생성자 (User 엔터티와 관계)

    private LocalDateTime startDate; // 시작 날짜
    private LocalDateTime endDate; // 종료 날짜

    @Enumerated(EnumType.STRING)
    private ProjectStatus status; // 프로젝트 상태 (진행 중, 완료 등)

    public enum ProjectStatus {
        FUNDING, SUCCESS, FAILED
    }
}
