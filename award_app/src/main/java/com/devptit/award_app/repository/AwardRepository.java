package com.devptit.award_app.repository;

import com.devptit.award_app.entity.Award;
import com.devptit.award_app.entity.AwardRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, String> {

}
