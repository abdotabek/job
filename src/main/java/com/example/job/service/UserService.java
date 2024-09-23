package com.example.job.service;

import com.example.job.exceptions.JobNotFoundException;
import com.example.job.exceptions.RequestParameterNullException;
import com.example.job.model.JobModel;
import com.example.job.model.UserModel;
import com.example.job.model.request.UserApplicationRequestModel;
import com.example.job.model.response.UserList;
import com.example.job.repository.JobRepository;
import com.example.job.repository.UserApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*****
 * Уровень обслуживания для всей обработки, связанной с пользователем. Этот класс несет ответственность
 * для применения нового пользователя, получения всех пользователей, создания модели пользователя.
 * **/
@Service
@Slf4j

public class UserService {
    /***
     * Репозиторий имеет доступ к таблице заданий в базе данных.
     * **/
    @Autowired
    JobRepository jobRepository;
    /***
     * Репозиторий имеет доступ к таблице заданий в базе данных.
     * **/
    @Autowired
    UserApplicationRepository userApplicationRepository;

    /*****
     * Этот метод используется пользователем для подачи заявки на новую вакансию. Он принимает запрос
     * model, создает модель пользователя и сохраняется в базе данных.
     * @param requestModel — модель, которая создается в UserModel для сохранения.
     * @throws JobNotFoundException
     * @throws RequestParameterNullException
     * **/
    public Long applyToJob(UserApplicationRequestModel requestModel) throws JobNotFoundException, RequestParameterNullException {
        if (requestModel.getUserEmail().isEmpty() || requestModel.getFirstName().isEmpty()
                || requestModel.getLastName().isEmpty() || requestModel.getPhoneNumber().isEmpty()
                || requestModel.getPrimarySkills().isEmpty() || requestModel.getJobId() == null) {
            throw new RequestParameterNullException("Request Parameters are Null");
        }
        UserModel model = createUserModel(requestModel);
        UserModel user = userApplicationRepository.save(model);
        log.info("User Application Details : {}", 1);
        return requestModel.getJobId();
    }

    /***
     * Удаляет заявку из базы данных, удаляя запись.
     * @param phoneNumber для удаления пользовательского приложения на основе записи.
     * **/
    public void withdrawApplication(String phoneNumber) {
        userApplicationRepository.deleteById(phoneNumber);
        log.info("User Application with phone Number {} Deleted", phoneNumber);
    }

    /******
     * Этот метод используется для создания модели пользователя на основе запроса, отправленного клиентом.
     * @param requestModel — модель, которая создается в UserModel.
     * @return Пользовательская модель
     * **/
    private UserModel createUserModel(UserApplicationRequestModel requestModel) throws JobNotFoundException {
        List<JobModel> jobIds = new ArrayList<>();
        if (jobRepository.findById(requestModel.getJobId()).isEmpty()) {
            throw new JobNotFoundException(requestModel.getJobId());
        }
        if (userApplicationRepository.existsById(requestModel.getPhoneNumber())) {
            log.info("User with phone number {} exist", requestModel.getPhoneNumber());
            if (userApplicationRepository.findById(requestModel.getPhoneNumber()).isPresent()) {
                jobIds = userApplicationRepository.findById(requestModel.getPhoneNumber()).get().getJobModels();
                log.info("Retrieved Job Ids {}", jobIds);
            }
        }
        jobIds.add(getJobModel(requestModel.getJobId()));
        return UserModel.builder()
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .userEmail(requestModel.getUserEmail())
                .phoneNumber(requestModel.getPhoneNumber())
                .primarySkills(requestModel.getPrimarySkills())
                .jobModels(jobIds).build();
    }

    /****
     * Этот метод получает список пользовательских моделей, сохраненных в базе данных.
     * **/
    private List<UserModel> getAllUsers() {
        return userApplicationRepository.findAll();
    }

    /****
     * Этот метод получает список UserList, сохраненный в базе данных.
     * **/
    public List<UserList> getUserList() {
        List<UserList> modelList = new ArrayList<>();
        for (UserModel m : getAllUsers()) {
            List<JobModel> jobModels = m.getJobModels();
            UserList userList = UserList.builder()
                    .userEmail(m.getUserEmail())
                    .firstName(m.getFirstName())
                    .lastName(m.getLastName())
                    .phoneNumber(m.getPhoneNumber())
                    .primarySkills(m.getPrimarySkills())
                    .jobId(jobModels)
                    .build();
            modelList.add(userList);
        }
        return modelList;
    }

    /***
     * Извлекает объект модели задания на основе заданного идентификатора.
     * ***/
    private List<JobModel> getJobModels(List<Long> ids) {
        List<JobModel> jobModels = new ArrayList<>();
        for (Long id : ids) {
            jobModels.add(jobRepository.findById(id).get());
        }
        return jobModels;
    }

    private JobModel getJobModel(Long id) {
        return jobRepository.findById(id).get();
    }

}
