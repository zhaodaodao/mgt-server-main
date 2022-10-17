package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.UserProvider;
import com.example.mgtserver.model.version2.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:46
 */
@Mapper
@Repository("userMapper")
public interface UserMapper {
    @InsertProvider(type = UserProvider.class, method = "insert")
    Integer insert(User user);

    @UpdateProvider(type = UserProvider.class, method = "update")
    Integer update(User user);

    @SelectProvider(type = UserProvider.class, method = "query")
    List<User>query(User user);

    @SelectProvider(type = UserProvider.class, method = "queryById")
    User queryById(Long id);

    @SelectProvider(type = UserProvider.class, method = "queryByUsername")
    User queryByUsername(String username);
}
