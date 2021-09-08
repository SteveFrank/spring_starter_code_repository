package com.starter.common.lib.example.dao;

import com.starter.common.lib.example.domain.Student;

/**
 * @author frankq
 * @date 2021/9/8
 */
public interface IStudentDao {

    Student queryStudentById(Long id);

}
