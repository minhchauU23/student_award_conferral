package com.devptit.award_app.repository;

import com.devptit.award_app.entity.AwardHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardHistoryRepository extends JpaRepository<AwardHistory, String> {
}
