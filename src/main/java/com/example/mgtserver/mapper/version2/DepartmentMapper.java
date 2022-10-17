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

    @DeleteProvider(type = DepartmentProvider.class, method = "delete")
    Integer delete(Long id);

}
