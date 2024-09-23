package com.example.job.exceptions;

/****
 * Это исключение генерируется, когда идентификатор задания не соответствует
 * соответствует любому идентификатору задания в базе данных.
 * **/
public class JobNotFoundException extends Exception {

    /***
     * Вызывает super для выдачи исключения.
     * @param id для идентификатора задания, которое не было найдено
     * */
    public JobNotFoundException(Long id) {
        System.out.printf("Job with id %n not found", id);
    }

}
