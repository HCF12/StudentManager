package cn.com.mapper;

import cn.com.pojo.StudentClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassMapper{
    List<StudentClass> getClassByGrade(@Param("gradeId")String gradeId);
}
