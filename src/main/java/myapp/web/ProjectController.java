package myapp.web;

import javax.annotation.PostConstruct;

import myapp.DirectoryManager;
import myapp.IDirectoryManager;
import myapp.jpa.model.Group;
import myapp.jpa.model.Person;
import myapp.jpa.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
@RequestMapping("/web")
public class ProjectController {

	@Autowired
	DirectoryManager manager;

	@Autowired
	User user;

	protected final Log logger = LogFactory.getLog(getClass());

	@PostConstruct
	public void start() {
		logger.info("ProjectController is used");
	}

	@RequestMapping("/list")
	public ModelAndView listGroups() {
		return new ModelAndView("group", "groups", manager.findAllGroups());
	}

	@RequestMapping("/new")
	public String newGroup() {
		final var group = new Group(String.format("UE %d", System.currentTimeMillis()));
		manager.saveGroup(group);
		return "redirect:/group/list";
	}

	@RequestMapping("/find")
	public ModelAndView findGroup(long groupId) {
		final var result = manager.findGroup(user,groupId);
		return new ModelAndView("group", "groups", result);
	}


	@ModelAttribute("user")
	public User newUser() {
		return user;
	}

	@RequestMapping(value = "/show")
	public String show() {
		logger.info("show user " + user);
		return "user";
	}

	@RequestMapping(value = "/login")
	public String login(@RequestParam("email_address") String email, @RequestParam("password") String password){
		logger.info("login user " + user);
		Person person = manager.findPerson(email);
		if (person.getPassword().equals(password)) {
			manager.login(user, person.getId(), password);
			user.setUsername(user.getPerson().getFirstName());
		}
		return "user";
	}

	@RequestMapping(value = "/logout")
	public String logout(){
		logger.info("logout user " + user);
		user.isLoggedIn = false;
		user.setPerson(null);
		user.setUsername("Unknown");
		return "user";
	}

}
