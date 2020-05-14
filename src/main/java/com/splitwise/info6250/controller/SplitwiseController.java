package com.splitwise.info6250.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.splitwise.info6250.model.Expense;
import com.splitwise.info6250.model.ExpenseDB;
import com.splitwise.info6250.model.ExpenseDocument;
import com.splitwise.info6250.model.FileBucket;
import com.splitwise.info6250.model.Friends;
import com.splitwise.info6250.model.Notification;
import com.splitwise.info6250.model.Tally;
import com.splitwise.info6250.model.TransactionDisplay;
import com.splitwise.info6250.model.TransactionHistory;
import com.splitwise.info6250.model.User;
import com.splitwise.info6250.model.UserProfile;
import com.splitwise.info6250.service.ExpenseDocumentService;
import com.splitwise.info6250.service.ExpenseService;
import com.splitwise.info6250.service.FriendService;
import com.splitwise.info6250.service.NotificationService;
import com.splitwise.info6250.service.TallyService;
import com.splitwise.info6250.service.TransactionService;
import com.splitwise.info6250.service.UserProfileService;
import com.splitwise.info6250.service.UserService;
import com.splitwise.info6250.validate.FileValidator;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class SplitwiseController {

	@Autowired
	UserService userService;
	
	@Autowired
	FriendService friendService;
	
	@Autowired
	TallyService tallyService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	TransactionService trService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
    ExpenseDocumentService userDocumentService;
 
    @Autowired
    FileValidator fileValidator;
     
    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
       binder.setValidator(fileValidator);
    }
	
	int logged_user_id=0;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		String str = "";
		String username = getPrincipal();
		User logged_user = userService.findBySSO(username);
		logged_user_id = logged_user.getId();
		List<User> adminusers = userService.findAllUsers();
		String logged_username = getPrincipal();
		List<User> users = new ArrayList<>();
		List<User> dispusers = new ArrayList<>();
		List<Friends> friendList = friendService.findBySourceId(logged_user_id);
		model.addAttribute("users", adminusers);
		for(User us:adminusers) {
			for(UserProfile up:us.getUserProfiles()) {
				if( (up.getType().equals("ADMIN") ) || (us.getSsoId().equals(logged_username)) ) {
					break;
				}
				else {
					users.add(us);
				}
			}
		}
		System.out.println("Users without admin: "+users);
		System.out.println("Users freind list: "+friendList);
		
		dispusers.clear();
		for(User u:users) {
			boolean flag = false;
			for(Friends f:friendList) {
				if(u.getSsoId().equals(f.getFriend_username())) {
					flag = true;
					System.out.println("User : "+u.getSsoId()+" exists in friendlist");
					break;
				}
				else {
					continue;
				}
			}
			if(flag == false) {
				System.out.println("User " + u.getSsoId()+" is getting added to display list:");
				dispusers.add(u);
			}
		}
		
		System.out.println("Users without friend list: "+dispusers);
		

		
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("user", dispusers);
		
		if(getUserRole().contains("ROLE_ADMIN")) {
		
			str = "splitwiseadminhome";
		}
		if(getUserRole().contains("ROLE_USER")) {
			str = "splitwiseuserhome";  //
		}
		return str;
	}
	
	
	@RequestMapping(value = { "/expenselist" }, method = RequestMethod.GET)
	public String listExpenses(ModelMap model) {
		String str = "";
		List<ExpenseDB> explist = expenseService.getAllExpenses(getPrincipal());
		List<Expense> explist1 = new ArrayList<>();
		for(ExpenseDB e:explist) {
			Expense exp = new Expense();
			exp.setId(e.getId());
			exp.setPaid_by(e.getPaid_by());
			exp.setSplit_with(e.getSplit_with().split("!"));
			exp.setAmount(e.getAmount());
			exp.setDescription(e.getDescription());
			explist1.add(exp);
		}
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("expenselist", explist1);
		str = "expenselist";
		return str;
	}

	@RequestMapping(value = { "/settleup-{user}" }, method = RequestMethod.GET)
	public String listExpenses(@PathVariable String user, ModelMap model) {
		String str = "";
		
		String pattern = "yyyy-MM-dd";
		String pattern1 = "HH:mm:ss";
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		SimpleDateFormat simpleDateFormat1 =new SimpleDateFormat(pattern1, new Locale("fr", "FR"));
		String date = simpleDateFormat.format(new Date());
		String time = simpleDateFormat1.format(new Date());
		
		Tally tally = tallyService.getTallyRecord(getPrincipal(), user);
		int amount = tally.getAmount();
		if(amount<0) {
			amount = amount*-1;
		}
		ExpenseDB exp = new ExpenseDB();
		exp.setAmount(amount);
		exp.setDate(date);
		exp.setTime(time);
		exp.setDescription(getPrincipal()+" settled up with "+user);
		exp.setPaid_by(getPrincipal());
		exp.setSplit_with(user);
		int exp_id = expenseService.addExpense(exp);
		trService.settleAmountTransaction(getPrincipal(), user, exp_id,amount);
		tally.setAmount(0);
		tallyService.updateTallyAmount(tally);
		Tally tally1 = tallyService.getTallyRecord(user,getPrincipal());
		tally1.setAmount(0);
		tallyService.updateTallyAmount(tally1);
		str = "individualTransaction";
		return str;
	}
	
	
	@RequestMapping(value = {"/transactions" }, method = RequestMethod.GET)
	public String getMyTransactions(ModelMap model) {
		String str = "";
		List<TransactionHistory> trlist = trService.getAllTransactions(getPrincipal());
		List<TransactionDisplay> tdlist = new ArrayList<TransactionDisplay>();
		
		List<Integer> contain = new ArrayList<>();
		for(int i=0;i<trlist.size();i++) {
			List<String> output;
			TransactionDisplay td;
			if(!contain.contains(i)) {
				td  = new TransactionDisplay();
				output = new ArrayList<String>();
				td.setTr_name(trlist.get(i).getTr_name());
				td.setExpense_id(trlist.get(i).getExpense_id());
				ExpenseDB expe = expenseService.getExpense(trlist.get(i).getExpense_id());
				td.setDate(expe.getDate());
				td.setTime(expe.getTime());
				if(trlist.get(i).getTr_name().contains(getPrincipal())) {
					td.setEdit(true);
				}
				else {
					td.setEdit(false);
				}
				output.add(trlist.get(i).getOutput());
			}
			else {
				continue;
			}
			if(i!=trlist.size()-1) {
			for(int j=i+1;j<trlist.size();j++) {
				if(trlist.get(i).getExpense_id().equals(trlist.get(j).getExpense_id())) {
					System.out.println("for i = "+i+" j = "+j+"  values matched");
					contain.add(j);
					output.add(trlist.get(j).getOutput());
					
				}
			}
			}
			td.setOutput(output);
			tdlist.add(td);
			
		}
		
		for(TransactionDisplay t:tdlist) {
			System.out.println(t.getTr_name());
			System.out.println(t.getOutput());
			System.out.println(t.isEdit());
		}
		
		model.addAttribute("transaction", tdlist);
//		model.addAttribute("loggedinuser", getPrincipal());
		str = "splitwiseuserhome5";
		return str;
	}
	
	@RequestMapping(value = {"/friends" }, method = RequestMethod.GET)
	public String getAllFriends(ModelMap model) {
		String str = "";
		List<User>users = new ArrayList<>();
		int user_id = userService.findBySSO(getPrincipal()).getId();
		logged_user_id = user_id;
		List<Friends> friendslist = friendService.findBySourceId(user_id);
		for(Friends fr:friendslist) {
			Tally tally = tallyService.getTallyRecord(getPrincipal(), fr.getFriend_username());
			if(tally!=null) {
				fr.setBalance(tally.getAmount());
			}
			else {
				fr.setBalance(0);
			}
		}
		
		
		model.addAttribute("friends", friendslist);
		model.addAttribute("loggedinuser", getPrincipal());
		str = "splitwiseuserhome2";
		return str;
	}
	
	
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String newUser1(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}
	
	@RequestMapping(value = { "/addexpense" }, method = RequestMethod.POST)
	public String addExpensePost(@Valid Expense expense,BindingResult result, @RequestParam("paid_by1") String paidBy,
			ModelMap model) {
		String Payer = "";
		String Splitter = "";
		boolean flag = true;
		String[] split = new String[1];
		
		if(expense.getSplit_with()==null) {
			Payer = paidBy;
			if(Payer.contains("Paid by YOU and split Equally with")) {
				Splitter = Payer.split(" ")[7];
				split[0] = Splitter;
				expense.setPaid_by(getPrincipal());
				expense.setSplit_with(split);
			}
			else if(Payer.contains("owes you the whole amount")) {
				Splitter = Payer.split(" ")[0];
				split[0] = Splitter;
				expense.setPaid_by(getPrincipal());
				expense.setSplit_with(split);
			}
			else {
				expense.setPaid_by(Payer.split(" ")[2]);
				Splitter = getPrincipal();
				split[0] = Splitter;
				expense.setSplit_with(split);
			}
			
		}else {
			Payer = expense.getPaid_by();
			System.out.println(Payer);
		}
		
		if(Payer.contains("the whole amount")) {
			flag = false;
		}
		System.out.println("Payer is: "+expense.getPaid_by());
		List<Friends> friendslist = friendService.findSuccessFriends(userService.findBySSO(expense.getPaid_by()).getId());
		List<String> friends = new ArrayList<String>();
		System.out.println("Friend list of :"+Payer);
		for(Friends f:friendslist) {
			friends.add(f.getFriend_username());
		}
		friends.add(Payer);
		for(String s:expense.getSplit_with()) {
			if(!friends.contains(s)) {
				System.out.println(s+" is not part of friend list");
				return "addexpense";
			}
		}
		
		String ss = "";
		for(int i=0; i<expense.getSplit_with().length; i++) {
			if(i<expense.getSplit_with().length-1) {
				ss = ss+expense.getSplit_with()[i]+"!";
			}
			else {
				ss = ss+expense.getSplit_with()[i];
			}
		}
		
		String pattern = "yyyy-MM-dd";
		String pattern1 = "HH:mm:ss";
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		SimpleDateFormat simpleDateFormat1 =new SimpleDateFormat(pattern1, new Locale("fr", "FR"));
		String date = simpleDateFormat.format(new Date());
		String time = simpleDateFormat1.format(new Date());
		
		ExpenseDB edb = new ExpenseDB();
		edb.setAmount(expense.getAmount());
		edb.setDescription(expense.getDescription());
		edb.setPaid_by(expense.getPaid_by());
		edb.setSplit_with(ss);
		edb.setDate(date);
		edb.setTime(time);
		int id = expenseService.addExpense(edb);
		
		System.out.println("Expense id: ssssssssssssssss"+id);
		expenseService.splitAmount(expense.getPaid_by(), expense.getSplit_with(), expense.getDescription(), expense.getAmount(), flag,id);
		model.addAttribute("expense",edb);
		model.addAttribute("success", "Expense for - " + edb.getDescription() + " $"+ edb.getAmount() + " added successfully");
		model.addAttribute("loggedinuser",getPrincipal());
		
		return "expensesuccess";
	}
	
	@RequestMapping(value = { "/edit-expense-{expenseId}" }, method = RequestMethod.POST)
	public String updateExpensePost(@Valid Expense expense,BindingResult result, @PathVariable int expenseId, @RequestParam("paid_by1") String paidBy,
			ModelMap model) {
		String Payer = "";
		String Splitter = "";
		boolean flag = true;
		String[] split = new String[1];
		
		if(expense.getSplit_with()==null) {
			Payer = paidBy;
			if(Payer.contains("Paid by YOU and split Equally with")) {
				Splitter = Payer.split(" ")[7];
				split[0] = Splitter;
				expense.setPaid_by(getPrincipal());
				expense.setSplit_with(split);
			}
			else if(Payer.contains("owes you the whole amount")) {
				Splitter = Payer.split(" ")[0];
				split[0] = Splitter;
				expense.setPaid_by(getPrincipal());
				expense.setSplit_with(split);
			}
			else {
				expense.setPaid_by(Payer.split(" ")[2]);
				Splitter = getPrincipal();
				split[0] = Splitter;
				expense.setSplit_with(split);
			}
			
		}else {
			Payer = expense.getPaid_by();
			System.out.println(Payer);
		}
		
		if(Payer.contains("the whole amount")) {
			flag = false;
		}
		System.out.println("Payer is: "+expense.getPaid_by());
		List<Friends> friendslist = friendService.findSuccessFriends(userService.findBySSO(expense.getPaid_by()).getId());
		
		List<String> friends = new ArrayList<String>();
		System.out.println("Friend list of :"+Payer);
		for(Friends f:friendslist) {
			friends.add(f.getFriend_username());
		}
		friends.add(Payer);
		for(String s:expense.getSplit_with()) {
			if(!friends.contains(s)) {
				System.out.println(s+" is not part of friend list");
				return "addexpense";
			}
		}
		String ss = "";
		for(int i=0; i<expense.getSplit_with().length; i++) {
			if(i<expense.getSplit_with().length-1) {
				ss = ss+expense.getSplit_with()[i]+"!";
			}
			else {
				ss = ss+expense.getSplit_with()[i];
			}
		}
		
		String pattern = "yyyy-MM-dd";
		String pattern1 = "HH:mm:ss";
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		SimpleDateFormat simpleDateFormat1 =new SimpleDateFormat(pattern1, new Locale("fr", "FR"));
		String date = simpleDateFormat.format(new Date());
		String time = simpleDateFormat1.format(new Date());
		
		ExpenseDB edb = new ExpenseDB();
		edb.setAmount(expense.getAmount());
		edb.setDescription(expense.getDescription());
		edb.setPaid_by(expense.getPaid_by());
		edb.setSplit_with(ss);
		edb.setDate(date);
		edb.setTime(time);
		
		ExpenseDB exp1 = expenseService.getExpense(expenseId);
		String paid_by = exp1.getPaid_by();
		String splits = exp1.getSplit_with();
		String[] split_with = {}; 
		if(splits.contains("!")) {
			split_with = splits.split("!");
		}
		else {
			split_with = new String[1];
			split_with[0] = splits;
		}
		List<TransactionHistory> list = trService.getTransactionByexpenseId(expenseId);
		String[] outputArray = list.get(0).getOutput().split(" ");
		int amount = 0;
		for(String value:outputArray) {
			if(value.startsWith("$")) {
				amount = Integer.parseInt(value.substring(1));
			}
		}
		expenseService.deleteExpense(expenseId);
		trService.deleteTransactions(expenseId);
		expenseService.RollbackTransaction(paid_by, split_with, amount);
		int id = expenseService.addExpense(edb);
		expenseService.splitAmount(expense.getPaid_by(), expense.getSplit_with(), expense.getDescription(), expense.getAmount(), flag,id);
		
		
		return "expensesuccess";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String saveUser1(@Valid User user, BindingResult result,
			ModelMap model) {
		for(UserProfile up:user.getUserProfiles()) {
			if(up.getType().equals("ADMIN")) {
				return "registrationDenied";
			}
		}
		
		if (result.hasErrors()) {
			return "registration";
		}

		
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		return "registrationsuccess";
	}
	
	
	
	@RequestMapping(value = { "/invitefriends" }, method = RequestMethod.GET)
	public String invitefriends(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "splitwiseuserhome3";
	}
	
	@RequestMapping(value = { "/notifications" }, method = RequestMethod.GET)
	public String invitenotification(ModelMap model) {
		List<Notification> notificationList = notificationService.getNotifications(logged_user_id);
		model.addAttribute("notifications", notificationList);
		model.addAttribute("loggedinuser", getPrincipal());
		return "splitwiseuserhome4";
	}
	
	@RequestMapping(value = { "/invitefriends" }, method = RequestMethod.POST)
	public String postinvitefriends(@RequestParam("email") String inviteemail) {
		System.out.println("Inside post invite friends"+inviteemail);
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("shubhamkawane10@gmail.com", "Maheshasha123@#"));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("Splitwise@admin.com");
			email.setSubject("Splitwise Invite");
			email.setMsg("Hi User, \n Your Friend has invited you to use Splitwise App.\n Please follow below link to SignUP into Splitwise!! \n http://localhost:8080/SplitwiseApp/register");
			email.addTo(inviteemail);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		return "splitwiseuserhome3";
	}
	
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "/addexpense" }, method = RequestMethod.GET)
	public String addExpense(ModelMap model) {
		List<Friends> friend = friendService.findSuccessFriends(logged_user_id);
		Expense expense = new Expense();
		String username = getPrincipal();
		List<String> lis = new ArrayList<>();
		lis.add(username);

		System.out.println("Paid by list"+lis);
		model.addAttribute("expense", expense);
		model.addAttribute("edit", false);
		model.addAttribute("friends",friend);
		model.addAttribute("paidby",lis);
		model.addAttribute("loggedinuser",getPrincipal());
		return "addexpense";
	}
	

	
	
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {
		System.out.println("user: "+user);
		System.out.println("result: "+result);
		if (result.hasErrors()) {
			return "registration";
		}

		
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "registrationsuccess";
	}

	@RequestMapping(value = {"/individualTransaction-{ssoId}" }, method = RequestMethod.GET)
	public String getIndividualTransaction(@PathVariable String ssoId, ModelMap model) {
		String str = "";
		
		List<TransactionHistory> list1 = trService.getIndividualTransactions(getPrincipal(), ssoId);
		List<TransactionDisplay> list = new ArrayList<TransactionDisplay>();
		String user = ssoId;
		for(TransactionHistory t:list1) {
			TransactionDisplay td = new TransactionDisplay();
			List<String> outputlist = new ArrayList<>();
			td.setExpense_id(t.getExpense_id());
			td.setTr_name(t.getTr_name());
			outputlist.add(t.getOutput());
			td.setOutput(outputlist);
			ExpenseDB expe = expenseService.getExpense(t.getExpense_id());
			td.setDate(expe.getDate());
			td.setTime(expe.getTime());
			list.add(td);
		}
		model.addAttribute("trlist", list);
		model.addAttribute("user", user);
		str = "individualTransaction";
		return str;
	}
	
	
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	@RequestMapping(value = { "/edit-expense-{expenseId}" }, method = RequestMethod.GET)
	public String editExpense(@PathVariable int expenseId,ModelMap model) {
		List<Friends> friend = friendService.findSuccessFriends(logged_user_id);
		ExpenseDB exp = expenseService.getExpense(expenseId);
		Expense expense = new Expense();
		expense.setPaid_by(exp.getPaid_by());
		if(exp.getSplit_with().contains("!")) {
			expense.setSplit_with(exp.getSplit_with().split("!"));
		}
		else {
			String[] splitwith = new String[1];
			splitwith[0] = exp.getSplit_with();
			expense.setSplit_with(splitwith);
		}
		expense.setDescription(exp.getDescription());
		expense.setAmount(exp.getAmount());
		
		String username = getPrincipal();
		List<String> lis = new ArrayList<>();
		lis.add(username);

		System.out.println("Paid by list"+lis);
		model.addAttribute("edit", true);
		model.addAttribute("expense",expense);
		model.addAttribute("friends",friend);
		model.addAttribute("paidby",lis);
		model.addAttribute("loggedinuser",getPrincipal());
		return "addexpense";
	}
	
	@RequestMapping(value = { "acceptrequest-{from_username}" }, method = RequestMethod.GET)
	public String acceptRequest(@PathVariable String from_username, ModelMap model) {
		User request_user = userService.findBySSO(from_username);//nitesh
		User logged_user = userService.findById(logged_user_id);//dharini
		
		Friends friend = new Friends();
		friend.setUser_id(logged_user.getId());
		friend.setFriend_username(request_user.getSsoId());
		friend.setFriendEmail(request_user.getEmail());
		friend.setFriendFirstName(request_user.getFirstName());
		friend.setFriendLastName(request_user.getLastName());
		friend.setStatus("Success");
		friendService.addFriend(friend);
		
		List<Friends> friendslist = friendService.findBySourceId(request_user.getId());
		for(Friends fir : friendslist) {
			if(fir.getFriend_username().equals(logged_user.getSsoId())) {
				System.out.println("name matched");
				friendService.updateFriend(fir);
			}
		}
		
		notificationService.deleteNotification(logged_user_id, request_user.getSsoId());
		
		return "redirect:/notifications";
	}
	
	@RequestMapping(value = { "rejectrequest-{from_username}" }, method = RequestMethod.GET)
	public String rejectRequest(@PathVariable String from_username, ModelMap model) {
		User request_user = userService.findBySSO(from_username);//nitesh
		User logged_user = userService.findById(logged_user_id);//dharini
		
		
		
		notificationService.deleteNotification(logged_user_id, request_user.getSsoId());
		
		return "redirect:/notifications";
	}
	
	@RequestMapping(value = { "/notify-{ssoId}" }, method = RequestMethod.GET)
	public String notifyUser(@PathVariable String ssoId, ModelMap model) {
		User target_user = userService.findBySSO(ssoId);
		User logged_user = userService.findById(logged_user_id);
		Notification notification = new Notification();
		notification.setUser_id(target_user.getId());
		notification.setFrom_username(logged_user.getSsoId());
		notification.setFromEmail(logged_user.getEmail());
		notification.setFromFirstName(logged_user.getFirstName());
		notification.setFromLastName(logged_user.getLastName());
		notificationService.addNotification(notification);
		
		Friends friend = new Friends();
		friend.setUser_id(logged_user.getId());
		friend.setFriend_username(target_user.getSsoId());
		friend.setFriendEmail(target_user.getEmail());
		friend.setFriendFirstName(target_user.getFirstName());
		friend.setFriendLastName(target_user.getLastName());
		friend.setStatus("Pending");
		friendService.addFriend(friend);
		
		
		return "redirect:/list";
	}
	
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		


		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	

	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}
	
	@RequestMapping(value = { "/delete-expense-{expenseId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable int expenseId) {
		ExpenseDB exp = expenseService.getExpense(expenseId);
		String paid_by = exp.getPaid_by();
		String split = exp.getSplit_with();
		String[] split_with = {}; 
		if(split.contains("!")) {
			split_with = split.split("!");
		}
		else {
			split_with = new String[1];
			split_with[0] = split;
		}
		List<TransactionHistory> list = trService.getTransactionByexpenseId(expenseId);
		String[] outputArray = list.get(0).getOutput().split(" ");
		int amount = 0;
		for(String value:outputArray) {
			if(value.startsWith("$")) {
				amount = Integer.parseInt(value.substring(1));
			}
		}
		expenseService.deleteExpense(expenseId);
		trService.deleteTransactions(expenseId);
		expenseService.RollbackTransaction(paid_by, split_with, amount);
		return "redirect:/transactions";
	}
	

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "splitwiselogin";
	    } else {
	    	return "redirect:/list";  
	    }  
	    
	}
	
//	@RequestMapping(value = "/login1", method = RequestMethod.GET)
//	public String loginPage1() {
//		if (isCurrentAuthenticationAnonymous()) {
//			return "splitwiselogin";
//	    } else {
//	    	return "redirect:/list";  
//	    }
//	}
	

	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}
	
	
	@RequestMapping(value = { "/add-document-{expenseId}" }, method = RequestMethod.GET)
    public String addDocuments(@PathVariable int expenseId, ModelMap model) {
		ExpenseDB expense = expenseService.getExpense(expenseId);
        model.addAttribute("expense", expense);
 
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
 
        List<ExpenseDocument> documents = userDocumentService.findAllByUserId(expenseId);
        model.addAttribute("documents", documents);
        model.addAttribute("loggedinuser",getPrincipal());
         
        return "managedocuments";
    }
     
 
    @RequestMapping(value = { "/download-document-{expenseId}-{docId}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int expenseId, @PathVariable int docId, HttpServletResponse response) throws IOException {
    	ExpenseDocument document = userDocumentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
  
        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
  
        return "redirect:/add-document-"+expenseId;
    }
 
    @RequestMapping(value = { "/delete-document-{expenseId}-{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int expenseId, @PathVariable int docId) {
        userDocumentService.deleteById(docId);
        return "redirect:/add-document-"+expenseId;
    }
 
    @RequestMapping(value = { "/add-document-{expenseId}" }, method = RequestMethod.POST)
    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int expenseId) throws IOException{
         
        if (result.hasErrors()) {
            System.out.println("validation errors");
            ExpenseDB expense = expenseService.getExpense(expenseId);
            model.addAttribute("expense", expense);
 
            List<ExpenseDocument> documents = userDocumentService.findAllByUserId(expenseId);
            model.addAttribute("documents", documents);
             
            return "managedocuments";
        } else {
             
            System.out.println("Fetching file");
             
            ExpenseDB expense = expenseService.getExpense(expenseId);
            model.addAttribute("expense", expense);
 
            saveDocument(fileBucket, expense);
 
            return "redirect:/add-document-"+expenseId;
        }
    }
     
    private void saveDocument(FileBucket fileBucket, ExpenseDB expense) throws IOException{
//    	System.out.println("multipart: "+fileBucket.getFile().getOriginalFilename());
        System.out.println("expense: "+expense.getDescription()); 
        ExpenseDocument document = new ExpenseDocument();
         
        MultipartFile multipartFile = fileBucket.getFile();
         
        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setExpense(expense);
        userDocumentService.saveDocument(document);
    }

	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("User authorities: "+((UserDetails)principal).getAuthorities());
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	private String getUserRole(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> userRole = ((UserDetails)principal).getAuthorities();
		
		return userRole.toString();
	}
	
	
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}


}
