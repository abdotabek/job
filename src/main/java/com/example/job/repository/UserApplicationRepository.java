package com.example.job.repository;

import com.example.job.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*****
 * Уровень репозитория, содержащий информацию о пользователях и заданиях, которые они применяли.
 * **/
@Repository
public interface UserApplicationRepository extends JpaRepository<UserModel, String> {
}
