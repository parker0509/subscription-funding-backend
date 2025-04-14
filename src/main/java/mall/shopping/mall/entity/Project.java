package mall.shopping.mall.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mall.shopping.mall.StringListConverter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String imageUrl;

    private int participants;

    @Column(nullable = false)
    @NotBlank
    private BigDecimal goalAmount;

    private BigDecimal raisedAmount = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creatorName;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public enum ProjectStatus {
        FUNDING, SUCCESS, FAILED
    }


    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> tags;


}

