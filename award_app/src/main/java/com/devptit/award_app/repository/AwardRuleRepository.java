package com.devptit.award_app.repository;

import com.devptit.award_app.entity.AwardRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRuleRepository extends JpaRepository<AwardRule, String> {
    @Query("SELECT ar FROM AwardRule ar INNER JOIN Award a ON ar.award.id = a.id AND a.id = :awardID")
    List<AwardRule> findByAward(@Param("awardID") String awardID);
}
