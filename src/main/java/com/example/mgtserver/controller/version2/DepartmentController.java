package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.version2.Department;
import com.example.mgtserver.service.version2.DepartmentService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("departmentController")
@RequestMapping("/api/v2/department")
@ResponseBody
public class DepartmentController {

    @Resource(name = "departmentService")
    private DepartmentService departmentService;

    /**
     * 创建部门信息
     * @param department 部门信息
     * @return 部门信息
     */
    @PostMapping("/create")
    public ResultVO<?> create(@RequestBody Department department) {
        int code = departmentService.create(department);
        return new ResultVO<>(ResponseCode.auto(code), code);
    }

    /**
     * 分页按部门查询
     * @param queryDTO 查询体
     * @return 查询结果
     */
    @PostMapping("/list")
    public ResultVO<PageInfo<Department>> list(@RequestBody PagedQueryDTO<Department> queryDTO) {
        PageInfo<Department> pageInfo = departmentService.list(queryDTO);
        return new ResultVO<>(ResponseCode.auto(pageInfo), pageInfo);
    }

    /**
     * 更新部门信息
     * @param department 部门信息
     * @return 更新后的部门信息
     */
    @PostMapping("/update")
    public ResultVO<?> update(@RequestBody Department department) {
        int code = departmentService.update(department);
        return new ResultVO<>(ResponseCode.auto(code), code);
    }

    /**
     * 根据编号移除部门信息
     * @param id 部门编号
     * @return 移除结果
     */
    @PostMapping("/remove")
    public ResultVO<?> remove(@RequestParam("id") Long id) {
        int code = departmentService.remove(id);
        return new ResultVO<>(ResponseCode.auto(code), null);
    }


}
