package com.example.mgtserver.mapper.version2;

import com.example.mgtserver.mapper.version2.provider.UserLoginStateProvider;
import com.example.mgtserver.model.version2.UserLoginState;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:46
 */
@Mapper
@Repository("userLoginStateMapper")
public interface UserLoginStateMapper {
    @InsertProvider(type = UserLoginStateProvider.class, method = "insert")
    Integer insert(UserLoginState loginState);

    @UpdateProvider(type = UserLoginStateProvider.class, method = "update")
    Integer update(UserLoginState loginState);

    @SelectProvider(type = UserLoginStateProvider.class, method = "queryByToken")
    UserLoginState queryByToken(String token);

    @SelectProvider(type = UserLoginStateProvider.class, method = "queryByUserId")
    List<UserLoginState> queryByUserId(Long userId);

    @DeleteProvider(type = UserLoginStateProvider.class, method = "deleteByToken")
    Integer deleteByToken(String token);

    @DeleteProvider(type = UserLoginStateProvider.class, method = "deleteByUserId")
    Integer deleteByUserId(Long userId);
}
