package Birhan.Registrtion_login.repository;

import Birhan.Registrtion_login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
