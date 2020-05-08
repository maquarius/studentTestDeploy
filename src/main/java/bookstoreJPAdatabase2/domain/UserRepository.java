package bookstoreJPAdatabase2.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserPerson, Long> {
	UserPerson findByUsername(String username);
}
