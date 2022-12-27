package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.dto.UserProjectDTO;
import com.example.mgtserver.mapper.version2.provider.DepartmentProvider;
import com.example.mgtserver.mapper.version2.provider.UserProjectProvider;
import com.example.mgtserver.model.version2.Department;
import com.example.mgtserver.model.version2.UserProject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("userProjectMapper")
public interface UserProjectMapper {

    @InsertProvider(type = UserProjectProvider.class, method = "insert")
    Integer insert(UserProject userProject);

    @Update("UPDATE user_project SET is_disable = 1, gmt_modify = #{currentTime} WHERE pid = #{pid} AND is_deleted = 0 AND uid = #{uid}")
    Integer delete(Long pid, Long uid, Long currentTime);

    @Update("UPDATE user_project SET is_disable = 0, gmt_modify = #{currentTime} WHERE pid = #{pid} AND is_deleted = 1 AND uid = #{uid}")
    Integer available(Long pid, Long uid, Long currentTime);

    @Select("SELECT * FROM user_project WHERE pid = #{pid} AND is_deleted = 0 AND uid = #{uid}")
    UserProject queryOne(Long pid, Long uid);

    @SelectProvider(type = UserProjectProvider.class, method = "queryListByPid")
    List<UserProject> queryListByPid(Long pid);

    @SelectProvider(type = UserProjectProvider.class, method = "query")
    List<UserProject> query(UserProject userProject);

    @UpdateProvider(type = UserProjectProvider.class, method = "update")
    Integer update(UserProject userProject);

    @Select("SELECT * FROM user")
    List<UserProjectDTO> queryAllUsers(Long pid);

}
