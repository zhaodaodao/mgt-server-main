package com.example.mgtserver.service.version2;

import com.example.mgtserver.dto.UserProjectDTO;
import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.UserMapper;
import com.example.mgtserver.mapper.version2.UserProjectMapper;
import com.example.mgtserver.model.ArtificialLayer;
import com.example.mgtserver.model.version2.Department;
import com.example.mgtserver.model.version2.User;
import com.example.mgtserver.model.version2.UserProject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserProjectService {

    @Resource
    private UserProjectMapper userProjectMapper;


    public int changeStatus(UserProject userProject) {

        // 如果已经存在这条数据，就更改状态
        if (queryOne(userProject.getPid(), userProject.getUid()) != null) {
            if (userProject.getIsDisable() == 0) {
                userProject.setIsDisable(1);
            }
            else {
                userProject.setIsDisable(0);
            }
            userProject.setGmtModify(System.currentTimeMillis());
            return userProjectMapper.update(userProject);
        }

        // 不存在则插入数据
        userProject.setGmtCreate(System.currentTimeMillis());
        userProject.setGmtModify(System.currentTimeMillis());

        return userProjectMapper.insert(userProject);
    }

    public int remove(Long pid, Long uid) {
        return userProjectMapper.delete(pid, uid, System.currentTimeMillis());
    }

    public UserProject queryOne(Long pid, Long uid) {
        return userProjectMapper.queryOne(pid, uid);
    }

    public int changeBatch(List<UserProject> userProjects) {
        for (UserProject userProject : userProjects) {
            if (changeStatus(userProject) == 0)  return 0;
        }
        return userProjects.size();
    }

    public List<UserProject> list(UserProject userProject) {
        return userProjectMapper.query(userProject);
    }

    public PageInfo<UserProjectDTO> listUserInfoByPid(PagedQueryDTO<UserProject> queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Long pid = queryDTO.getQuery().getPid();
        List<UserProjectDTO> userProjectDTOList = userProjectMapper.queryAllUsers(pid);
        return new PageInfo<>(userProjectDTOList);
    }


}
