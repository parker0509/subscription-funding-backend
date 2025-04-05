package mall.shopping.mall.repository;

import mall.shopping.mall.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {
    // 필요한 메서드들...
}

