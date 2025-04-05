package mall.shopping.mall.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funding")
@Getter
@Setter
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;  // 펀딩 제목
    private Long targetAmount;  // 목표 금액
    private Long currentAmount;  // 현재 모금액
    private LocalDateTime endDate;  // 펀딩 마감일

    @Enumerated(EnumType.STRING)
    private FundingStatus status;  // 진행 상태 (진행중, 성공, 실패)

    // 연관 관계 설정
    @OneToMany(mappedBy = "funding")
    private List<FundingSupport> supports = new ArrayList<>();
}


