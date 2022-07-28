package cl.developteam.repository.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.developteam.repository.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
