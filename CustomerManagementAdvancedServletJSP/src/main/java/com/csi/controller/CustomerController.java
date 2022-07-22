package com.csi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csi.model.Customer;
import com.csi.service.CustomerService;
import com.csi.service.CustomerServiceImpl;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String INDEX_PAGE = "/index.jsp";
	String SIGNUP_PAGE = "/signup.jsp";
	String SIGNIN_PAGE = "/signin.jsp";
	String SHOWDATA_PAGE = "/show.jsp";
	String EDITDATA_PAGE = "/edit.jsp";

	CustomerService customerServiceImpl = new CustomerServiceImpl();

	public CustomerController() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String redirect = "";

		String customerId = request.getParameter("custid");

		String action = request.getParameter("action");

		if (customerId != null && action.equals("signup")) {

			int custId = Integer.parseInt(customerId);
			String custName = request.getParameter("custname");
			String custAddress = request.getParameter("custaddress");
			int custAge = Integer.parseInt(request.getParameter("custage"));
			long custContactNumber = Long.valueOf(request.getParameter("custcontactnumber"));
			double custAccountBalance = Double.valueOf(request.getParameter("custaccountbalance"));
			Date dobDate = null;

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			try {
				dobDate = dateFormat.parse(request.getParameter("custdob"));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String custEmailId = request.getParameter("custemailid");

			String custPassword = request.getParameter("custpassword");

			Customer customer = new Customer(custId, custName, custAddress, custAge, custContactNumber,
					custAccountBalance, dobDate, custEmailId, custPassword);

			customerServiceImpl.signUp(customer);

			redirect = SIGNIN_PAGE;

			System.out.println("Redirected to SignIn Page");

		} else if (action.equals("signin")) {

			String custEmailId = request.getParameter("custemailid");

			String custPassword = request.getParameter("custpassword");

			if (customerServiceImpl.signIn(custEmailId, custPassword)) {

				List<Customer> customerList = new ArrayList<Customer>();
				customerList = customerServiceImpl.getAllData();
				request.setAttribute("customerList", customerList);

				redirect = SHOWDATA_PAGE;

			} else {
				redirect = SIGNIN_PAGE;
			}

		} else if (action.equals("editform")) {
			redirect = EDITDATA_PAGE;
		} else if (action.equals("edit")) {

			int custId = Integer.parseInt(request.getParameter("custid"));
			String custName = request.getParameter("custname");
			String custAddress = request.getParameter("custaddress");
			int custAge = Integer.parseInt(request.getParameter("custage"));
			long custContactNumber = Long.valueOf(request.getParameter("custcontactnumber"));
			double custAccountBalance = Double.valueOf(request.getParameter("custaccountbalance"));
			Date dobDate = null;

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			try {
				dobDate = dateFormat.parse(request.getParameter("custdob"));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String custEmailId = request.getParameter("custemailid");

			String custPassword = request.getParameter("custpassword");

			Customer customer = new Customer(custId, custName, custAddress, custAge, custContactNumber,
					custAccountBalance, dobDate, custEmailId, custPassword);

			customerServiceImpl.updateData(custId, customer);

			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.getAllData();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

			System.out.println("Redirected to SHOWDATA Page");

		} else if (action.equals("delete")) {

			int custId = Integer.parseInt(request.getParameter("custid"));

			customerServiceImpl.deleteById(custId);
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.getAllData();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		} else if (action.equals("deletealldata")) {

			customerServiceImpl.deleteAllData();
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.getAllData();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		} else if (action.equals("show")) {

			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.getAllData();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		} else if (action.equals("sortbyname")) {
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.sortByName();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		} else if (action.equals("sortbyage")) {
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.sortByAge();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		} else if (action.equals("sortbyaccountbalance")) {
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.sortByAccountBalance();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		} else if (action.equals("sortbydob")) {
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.sortByDOB();
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		} else if (action.equals("filterbyaccountbalance")) {

			double custAccountBalance = Double.valueOf(request.getParameter("custaccountbalance"));
			List<Customer> customerList = new ArrayList<Customer>();
			customerList = customerServiceImpl.filterByAccpountBalance(custAccountBalance);
			request.setAttribute("customerList", customerList);

			redirect = SHOWDATA_PAGE;

		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
