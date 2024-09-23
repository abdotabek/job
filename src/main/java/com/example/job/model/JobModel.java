package com.example.job.model;

import com.example.job.enums.JobType;
import com.example.job.enums.WorkModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/****
 * Это объект задания, который сохраняется в базе данных.
 * **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_model")
public class JobModel {
    /**
     * Параметр для хранения идентификатора задания.
     * Также является уникальным идентификатором для всех заданий.
     * */
    @Id
    @Column(name = "ID")
    private Long jobId;
    /**
     * Параметр для хранения названия должности.
     * */
    private String jobTitle;
    /**
     * Параметр для хранения описания задания.
     * */
    @Column(length = 65555)
    private String jobDescription;
    /**
     * Параметр для хранения местоположения работы.
     * */
    private String jobLocation;
    /**
     * Параметр для хранения названия компании.
     * */
    private String companyName;
    /**
     * Параметр для хранения навыков, необходимых для работы, в виде списка.
     * */
    private List<String> requiredSkills;
    /**
     * Параметр для хранения типа задания.
     * */
    private JobType jobType;
    /**
     * Параметр для хранения рабочей модели.
     * */
    private WorkModel workModel;
    /****
     * Распечатывает объект модели задания в строковой форме.
     * **/
    @Override
    public String toString() {
        return "JobModel{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobLocation='" + jobLocation + '\'' +
                ", requiredSkills=" + requiredSkills +
                ", jobType=" + jobType +
                ", companyName=" +companyName+
                ", workModel=" + workModel +
                '}';
    }
}
