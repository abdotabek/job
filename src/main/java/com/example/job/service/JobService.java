package com.example.job.service;

import com.example.job.model.JobModel;
import com.example.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/****
 * Сервисный уровень для всей обработки, связанной с заданием. Этот класс несет ответственность
 * для добавления нового задания и получения списка всех заданий в репозитории.
 *
 * **/
@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    /*****
     * Этот метод отвечает за добавление нового задания в базу данных.
     * @param jobModel Объект, который сохраняется в базе данных.
     * @return идентификатор задания
     * **/
    public Long addJob(JobModel jobModel) {
        JobModel returned = jobRepository.save(jobModel);
        System.out.println(returned);
        return returned.getJobId();
    }
    /*****
     * Этот метод получает все задания, имеющиеся в базе данных.
     * @return List<JobModel>
     * **/
    public List<JobModel> getAllJobs() {
        return jobRepository.findAll();
    }
    /*****
     * Этот метод удаляет задание по идентификатору.
     * @param id для удаления задания на основе заданного идентификатора.
     * **/
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }
}
