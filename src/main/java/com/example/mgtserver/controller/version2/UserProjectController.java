package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.UserProjectDTO;
import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.version2.Department;
import com.example.mgtserver.model.version2.UserProject;
import com.example.mgtserver.service.version2.UserProjectService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/api/v2/user_project")
@RestController
public class UserProjectController {

    @Resource
    private UserProjectService userProjectService;

    @PostMapping("/update")
    public ResultVO<?> changeStatus(@RequestBody UserProject userProject) {
        int code = userProjectService.changeStatus(userProject);
        return new ResultVO<>(ResponseCode.auto(code), code);
    }

    @PostMapping("/list")
    public ResultVO<PageInfo<UserProjectDTO>> list(@RequestBody PagedQueryDTO<UserProject> queryDTO) {
        PageInfo<UserProjectDTO> pageInfo = userProjectService.listUserInfoByPid(queryDTO);
        return new ResultVO<>(ResponseCode.auto(pageInfo), pageInfo);
    }

}
