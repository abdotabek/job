package com.example.job.model.response;

import com.example.job.model.JobModel;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
/*****
 * Этот класс представляет список пользователей, сохраненный в базе данных, со встроенными моделями заданий.
 * **/
public class UserList {
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
     * Параметр для хранения основных навыков пользователя в виде списка.
     */
    private List<String> primarySkills;
    /**
     * Параметр для хранения списка моделей должностей, к которым пользователь подал заявку.
     */
    @NonNull
    private List<JobModel> jobId;
}
