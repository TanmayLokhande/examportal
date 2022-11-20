package com.smartContact.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smartContact.Entities.Contact;
import com.smartContact.Entities.User;
import com.smartContact.Helper.Message;
import com.smartContact.dao.ContactRepository;
import com.smartContact.dao.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;

	@ModelAttribute
	public void AddCommonData(Model model, Principal principal) {

		String name = principal.getName();

		User user = this.userRepository.getUserByUserName(name);
		model.addAttribute("user", user);

	}

	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {

		model.addAttribute("title", "User Dashboard");
		return "normal/user_dasboard";
	}

	
	// open add form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact",new Contact());
		return "normal/add_contact_form";
	
	}
	
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact,@RequestParam("profileImage") MultipartFile file,  Principal principal,HttpSession session) {
		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			
			if( file.isEmpty() ) {
				System.out.println("File is empty");
			}
			else {
				contact.setImage(file.getOriginalFilename());
				
				File file2 = new ClassPathResource("static/images").getFile();
				
				Path path = Paths.get(file2.getAbsolutePath() + File.separator + file.getOriginalFilename());
				
				Files.copy(file.getInputStream(),path , StandardCopyOption.REPLACE_EXISTING);
				
				System.out.println("Image is uploaded");
				
			}
			
			contact.setUser(user);
			
			user.getContacts().add(contact);
			
				
			this.userRepository.save(user);
			
			System.out.println(contact);
			
			session.setAttribute("message",new Message("Your contact is added !!", "success") );
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			session.setAttribute("message",new Message("Something went wrong,Try Again !!", "danger") );

			
		}
		return "normal/add_contact_form";
	}
	
	@GetMapping("/show-contacts/{page}")
	public String showContacts( @PathVariable("page") Integer page,Model model,Principal principal ) {
		model.addAttribute("title","Show user contacts");
		
		String userName = principal.getName();
		
		User user = this.userRepository.getUserByUserName(userName);
		
		Pageable pageable =  PageRequest.of(page, 5);
		Page<Contact> contacts =  this.contactRepository.findContactsByUser(user.getId(),pageable);
		
		model.addAttribute("contacts",contacts);
		
		model.addAttribute("currentPage",page);
		
		model.addAttribute("totalPages",contacts.getTotalPages());
		
		return "normal/show-contacts";
		
	}
	
	@RequestMapping("/{cId}/contact")
	public String showContactDetails(@PathVariable("cId") Integer cId,Model model) {
		System.out.println(cId);
		Optional<Contact> contactOptional =  this.contactRepository.findById(cId);
		Contact contact =  contactOptional.get();
		
		model.addAttribute("contact",contact);
		
;		return "normal/contact_detail";
	}
	
	

}
