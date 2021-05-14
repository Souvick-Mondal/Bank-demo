package a;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
	@Autowired
	private AccountService service;
	@RequestMapping("/")
	public String viewHomePage(Model model) {
        List<Account> listAccounts = service.listAll();
        model.addAttribute("listProducts", listAccounts);
       
        return "index";
	}
	
	 @RequestMapping("/new")
	    public String showNewProductPage(Model model) {
	       Account account = new Account();
	        model.addAttribute("account", account);
	         
	        return "new_account";
	 }
	 
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveAccount(@ModelAttribute("account")Account account) {
	        service.save(account);
	         
	        return "redirect:/";
	 }
	 @RequestMapping("/edit/{id}")
	    public ModelAndView showEditAccountPage(@PathVariable(name = "id") Long id) {
	        ModelAndView mav = new ModelAndView("edit_account");
	        Account account = service.get(id);
	        mav.addObject("account", account);
	         
	        return mav;
	    }
	 @RequestMapping("/delete/{id}")
	    public String deleteAccount(@PathVariable(name = "id") Long id) {
	        service.delete(id);
	        return "redirect:/";       
	    }
}
