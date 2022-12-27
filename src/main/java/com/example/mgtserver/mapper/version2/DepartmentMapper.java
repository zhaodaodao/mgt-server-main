package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.DepartmentProvider;
import com.example.mgtserver.model.version2.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("departmentMapper")
public interface DepartmentMapper {

    @InsertProvider(type = DepartmentProvider.class, method = "insert")
    Integer insert(Department department);

    @UpdateProvider(type = DepartmentProvider.class, method = "update")
    Integer update(Department department);

    @SelectProvider(type = DepartmentProvider.class, method = "query")
    List<Department> query(Department department);

    @Update("UPDATE department SET is_disable = 1, gmt_modify = #{currentTime} WHERE id = #{id} AND is_deleted = 0 LIMIT 1")
    Integer delete(Long id, Long currentTime);

}
