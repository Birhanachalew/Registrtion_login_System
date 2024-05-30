package Birhan.Registrtion_login.service;

import Birhan.Registrtion_login.dto.UserDto;
import Birhan.Registrtion_login.entity.Role;
import Birhan.Registrtion_login.entity.User;
import Birhan.Registrtion_login.repository.RoleRepository;
import Birhan.Registrtion_login.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


@Override
public Void saveUser(UserDto userDto){
    User user = new User();
    user.setName(userDto.getFirstName() + " " userDto.getLatName());
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    Role role = roleRepository.findByName("ROLE_ADMIN");
    if(role == null){
        role = checkRoleExist();
    }

    @Override
            public  User findUserByEmail(String email){
        return userRepository.findByEmail(email);

    }

    @Override
            public List<UserDto> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> mapToUserDto(user)).collect(collectors.toList());

    }
    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split("");
        userDto.setFirstName(str[0]);
        userDto.setLasName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;


    }
private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);

    }




}