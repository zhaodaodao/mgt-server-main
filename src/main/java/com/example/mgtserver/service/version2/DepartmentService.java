package com.example.mgtserver.service.version2;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.DepartmentMapper;
import com.example.mgtserver.model.KmlLayer;
import com.example.mgtserver.model.version2.Department;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("departmentService")
public class DepartmentService {

    @Resource(name = "departmentMapper")
    private DepartmentMapper departmentMapper;

    public PageInfo<Department> list(PagedQueryDTO<Department> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Department department = queryDTO.getQuery();
        List<Department> list = departmentMapper.query(department);
        return new PageInfo<>(list);
    }

    public int create(Department department) {
        return departmentMapper.insert(department);
    }

    public int update(Department department) {
        return departmentMapper.update(department);
    }


    public int remove(Long id) {
        return departmentMapper.delete(id);
    }
}
