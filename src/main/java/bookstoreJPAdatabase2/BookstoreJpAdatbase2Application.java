package bookstoreJPAdatabase2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstoreJPAdatabase2.domain.Department;
import bookstoreJPAdatabase2.domain.DepartmentRepository;
import bookstoreJPAdatabase2.domain.Student;
import bookstoreJPAdatabase2.domain.StudentRepository;
import bookstoreJPAdatabase2.domain.UserPerson;
import bookstoreJPAdatabase2.domain.UserRepository;

@SpringBootApplication
public class BookstoreJpAdatbase2Application {
	private static final Logger Log = LoggerFactory.getLogger(BookstoreJpAdatbase2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreJpAdatbase2Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(StudentRepository repository, DepartmentRepository drepository,
			UserRepository urepository) {
		return (args) -> {

			Log.info("create new departments");
			drepository.save(new Department("science"));
			drepository.save(new Department("arts"));
			drepository.save(new Department("social"));

			Log.info("save one student");
			repository.save(new Student("Marius", "Busse", "email@email.cl", drepository.findByName("science").get(0)));
			repository.save(
					new Student("Sofia", "Liljeström", "sdaemail@email.cl", drepository.findByName("arts").get(0)));
			repository.save(new Student("sdfsd", "Bussesdfds", "emadsfdsfil@email.cl",
					drepository.findByName("social").get(0)));
			repository.save(new Student("Maridsfdsus", "Busdsfse", "emailddddd@email.cl",
					drepository.findByName("science").get(0)));

			Log.info("Creating some Users");
			UserPerson user1 = new UserPerson("user2", "$2a$10$kkGrBvtYWvSlEeiNR9tgRuMQ1auKbdCi/NzuDthsJ/5YbK6kZIQLC", "USER");
			UserPerson user2 = new UserPerson("admin2", "$2a$10$82i27yeufHQnPBHQceTNVuFJkwsKnZfx.VLmoeWm7jiNSeoSdAuvW", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}

}
