package Birhan.Registrtion_login.service;

import Birhan.Registrtion_login.dto.UserDto;
import Birhan.Registrtion_login.entity.User;

import java.util.List;

public interface UserService {
    Void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    List<UserDto> findAllUser();
}
