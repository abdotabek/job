package com.example.job.repository;

import com.example.job.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*****
 * Слой репозитория, содержащий информацию о Джобсе.
 * **/
@Repository
public interface JobRepository extends JpaRepository<JobModel, Long> {

}
