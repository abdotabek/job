package com.example.job.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/****
 * Этот класс представляет запрос JSON, который примет контроллер.
 * и создайте UserModel для сохранения в базе данных.
 * **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApplicationRequestModel {
    /**
     * Параметр для хранения имени пользователя.
     */
    private String firstName;
    /**
     * Параметр для хранения фамилии пользователя.
     */
    private String lastName;
    /**
     * Параметр для хранения электронной почты пользователя.
     */
    @NonNull
    private String userEmail;
    /**
     * Параметр для хранения номера телефона пользователя.
     */
    @NonNull
    private String phoneNumber;
    /**
     * Параметр для хранения основных навыков пользователя.
     */
    private List<String> primarySkills;
    /**
     * Параметр для хранения списка идентификаторов заданий.
     * представляет вакансии, на которые подал заявку пользователь.
     */
    private Long jobId;
}
