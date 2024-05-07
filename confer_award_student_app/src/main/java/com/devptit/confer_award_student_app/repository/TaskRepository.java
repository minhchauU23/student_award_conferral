package com.devptit.confer_award_student_app.repository;

import com.devptit.confer_award_student_app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String > {
}
