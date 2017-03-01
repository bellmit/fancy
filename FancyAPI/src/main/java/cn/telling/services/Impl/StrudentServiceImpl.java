package cn.telling.services.Impl;

import cn.telling.dao.StudentDao;
import cn.telling.model.Student;
import cn.telling.services.StudentService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by Administrator on 16-1-4.
 **/

@Service
public class StrudentServiceImpl implements StudentService{

    @Resource
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
       return studentDao.add(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.update(student);
    }

    @Override
    public Student findOne(Integer id) {
        return studentDao.findOne(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
