package bookstoreJPAdatabase2.web;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookstoreJPAdatabase2.domain.DepartmentRepository;
import bookstoreJPAdatabase2.domain.Message;
import bookstoreJPAdatabase2.domain.Student;
import bookstoreJPAdatabase2.domain.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	private StudentRepository repository;
	@Autowired
	private DepartmentRepository drepository;

	private final AtomicLong counter = new AtomicLong();

	
	//Log in 
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	


	//Displays students in thymeleaf template
	@RequestMapping(value = "/studentlist")
	public String studentList(Model model) {
		model.addAttribute("students", repository.findAll());
		return "studentlist";
	}
	
	//Display students in RESTful way
	@RequestMapping(value="/students", method = RequestMethod.GET)
	public @ResponseBody List<Student> studentListRest(){
		return(List<Student>)repository.findAll();
	}

	//Find student by ID RESTfull service
	@RequestMapping(value="/student/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Student> findStudentRest(@PathVariable("id")Long studentId) {
	return repository.findById(studentId);
}
	
	@RequestMapping(value = "/add")
	public String addStudent(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("departments", drepository.findAll());
		return "addStudent";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Student student) {
		repository.save(student);
		return "redirect:studentlist";
	}

	@RequestMapping("/hello")
	public @ResponseBody Message greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Message(counter.incrementAndGet(), "Hello" + name);

	}

	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
		repository.deleteById(studentId);
		return "redirect:../studentlist";
	}

	@RequestMapping(value = "/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editStudent(@PathVariable("id") Long studentId, Model model) {
		model.addAttribute("student", repository.findById(studentId));
		model.addAttribute("departments", drepository.findAll());
		return "editstudent";
	}
}
