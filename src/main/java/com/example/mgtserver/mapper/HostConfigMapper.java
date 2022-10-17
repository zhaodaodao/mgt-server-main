package com.example.mgtserver.mapper;

import com.example.mgtserver.model.HostConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HostConfigMapper {
    @Select("SELECT * FROM tb_config WHERE env = #{env}")
    HostConfig queryEnv(String env);

    @Update("UPDATE tb_config SET port = #{port} WHERE env = #{env}")
    void updatePort(String port, String env);
}
