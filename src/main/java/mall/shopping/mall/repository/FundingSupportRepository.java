package mall.shopping.mall.repository;

import mall.shopping.mall.entity.FundingSupport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundingSupportRepository extends JpaRepository<FundingSupport, Long> {

    List<FundingSupport> findByFundingId(Long fundingId);
}
