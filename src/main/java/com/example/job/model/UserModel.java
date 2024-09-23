package com.example.job.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/****
 * Это объект пользователя, который сохраняется в базе данных.
 * **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserModel {
    /**
     * Параметр для хранения имени пользователя.
     * */
    private String firstName;
    /**
     * Параметр для хранения фамилии пользователя.
     * */
    private String lastName;
    /**
     * Параметр для хранения электронной почты пользователя. Не может быть нулевым.
     * */
    @NonNull
    private String userEmail;
    @NonNull
    @Id
    /**
     * Параметр для хранения номера телефона пользователя, а также уникального идентификатора таблицы пользователей.
     * Не может быть нулевым.
     * */
    private String phoneNumber;
    /**
     * Параметр для хранения основных навыков пользователя в виде списка.
     * */
    private List<String> primarySkills;
    /**
     * Параметр для хранения списка моделей должностей.
     * представляет вакансии, на которые подал заявку пользователь.
     * */
    @NonNull
    @OneToMany
    @JoinColumn(name = "jobs")
    private List<JobModel> jobModels;
    /****
     * Распечатывает объект модели пользователя в строковой форме.
     * **/
    @Override
    public String toString() {
        return "UserModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", primarySkills=" + primarySkills +
                ", jobId=" + jobModels +
                '}';
    }

}
