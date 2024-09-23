package com.example.job.controller;

import com.example.job.model.JobModel;
import com.example.job.model.request.UserApplicationRequestModel;
import com.example.job.model.response.UserList;
import com.example.job.service.JobService;
import com.example.job.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*****
 * Это класс контроллера, который отображает входящие запросы от
 * http://localhost:3000 к конечной точке api/v1/jobs. Каждый запрос сопоставляется с
 * конкретный метод, в зависимости от Типа запроса.
 *
 * */
@RestController
@RequestMapping("api/v1/jobs")
@Slf4j
@CrossOrigin("http://localhost:3000")
public class JobController {
    /***
     * Параметры, предоставляющие доступ к классу JobService.
     * **/
    @Autowired
    JobService jobService;
    /***
     * Параметры, предоставляющие доступ к классу UserService.
     * **/
    @Autowired
    UserService userService;

    /***
     * Сопоставляет запрос POST для добавления новой записи о задании в базу данных.
     * @param jobModel Запись о задании, которую необходимо добавить в базу данных.
     * @return Long Идентификатор добавленного задания.
     * **/

    @PostMapping(path = "add-job")
    public Long addJob(@RequestBody JobModel jobModel) {
        return jobService.addJob(jobModel);
    }

    /***
     * Сопоставляет запрос GET для получения списка всех вакансий в базе данных.
     * @return List<JobModel> Список всех записей о заданиях, присутствующих в базе данных.
     * **/
    @GetMapping
    public ResponseEntity<List<JobModel>> getJobs() {
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    /***
     * Сопоставляет запрос POST для добавления новой записи о задании в базу данных.
     * @param requestModel Сущность запроса со стороны клиента, которую клиент должен применить к заданию.
     * @throws Exception
     * **/
    @PostMapping("/apply")
    public void applyToJob(@RequestBody UserApplicationRequestModel requestModel) throws Exception {
        Long id = userService.applyToJob(requestModel);
        log.info("Applied to job : {}", id);
    }

    /***
     * Сопоставляет запрос GET для получения списка всех пользователей в базе данных.
     * @return List<UserList> Список всех записей пользователей, присутствующих в базе данных.
     * **/
    @GetMapping(path = "/usersList")
    public ResponseEntity<List<UserList>> getUsers() {
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }
}
