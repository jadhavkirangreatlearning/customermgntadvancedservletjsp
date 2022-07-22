package com.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.csi.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	Connection connection = null;

	public CustomerDaoImpl() {
		// TODO Auto-generated constructor stub

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CustomerManagement", "root",
						"root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void signUp(Customer customer) {
		// TODO Auto-generated method stub

		String INSERT_SQL = "insert into customer(custid, custname, custaddress, custage, custcontactnumber, custaccountbalance, custdob, custemailid, custpassword)values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

			preparedStatement.setInt(1, customer.getCustId());
			preparedStatement.setString(2, customer.getCustName());
			preparedStatement.setString(3, customer.getCustAddress());
			preparedStatement.setInt(4, customer.getCustAge());
			preparedStatement.setLong(5, customer.getCustContactNumber());
			preparedStatement.setDouble(6, customer.getCustAccountBalance());
			java.sql.Date dob = new java.sql.Date(customer.getCustDOB().getTime());
			preparedStatement.setDate(7, dob);
			preparedStatement.setString(8, customer.getCustEmailId());
			preparedStatement.setString(9, customer.getCustPassword());

			preparedStatement.executeUpdate();

			System.out.println("Data Inserted into DB Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean signIn(String custEmailId, String custPassword) {
		// TODO Auto-generated method stub

		boolean flag = false;

		for (Customer customer : getAllData()) {
			if (customer.getCustEmailId().equals(custEmailId) && customer.getCustPassword().equals(custPassword)) {
				flag = true;
			}
		}

		return flag;
	}

	@Override
	public Customer getDataById(int custId) {
		// TODO Auto-generated method stub

		Customer cust = new Customer();

		for (Customer customer : getAllData()) {
			if (customer.getCustId() == custId) {
				cust.setCustId(customer.getCustId());
				cust.setCustName(customer.getCustName());
				cust.setCustAddress(customer.getCustAddress());
				cust.setCustAge(customer.getCustAge());
				cust.setCustContactNumber(customer.getCustContactNumber());
				cust.setCustAccountBalance(customer.getCustAccountBalance());
				cust.setCustDOB(customer.getCustDOB());
				cust.setCustEmailId(customer.getCustEmailId());
				cust.setCustPassword(customer.getCustPassword());
			}
		}

		return cust;
	}

	@Override
	public List<Customer> getAllData() {
		// TODO Auto-generated method stub

		List<Customer> customers = new ArrayList<Customer>();

		String GETALLDATA_SQL = "select * from customer";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GETALLDATA_SQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Customer customer = new Customer();

				customer.setCustId(resultSet.getInt(1));
				customer.setCustName(resultSet.getString(2));
				customer.setCustAddress(resultSet.getString(3));
				customer.setCustAge(resultSet.getInt(4));
				customer.setCustContactNumber(resultSet.getLong(5));
				customer.setCustAccountBalance(resultSet.getDouble(6));
				customer.setCustDOB(resultSet.getDate(7));
				customer.setCustEmailId(resultSet.getString(8));
				customer.setCustPassword(resultSet.getString(9));

				customers.add(customer);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customers;
	}

	@Override
	public void updateData(int custId, Customer customer) {
		// TODO Auto-generated method stub

		String UPDATE_SQL = "update customer set custname=?, custaddress=?, custage=?, custcontactnumber=?, custaccountbalance=?, custdob=?, custemailid=?, custpassword=? where custid=?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);

			preparedStatement.setString(1, customer.getCustName());
			preparedStatement.setString(2, customer.getCustAddress());
			preparedStatement.setInt(3, customer.getCustAge());
			preparedStatement.setLong(4, customer.getCustContactNumber());
			preparedStatement.setDouble(5, customer.getCustAccountBalance());
			java.sql.Date dob = new java.sql.Date(customer.getCustDOB().getTime());
			preparedStatement.setDate(6, dob);
			preparedStatement.setString(7, customer.getCustEmailId());
			preparedStatement.setString(8, customer.getCustPassword());
			preparedStatement.setInt(9, custId);
			preparedStatement.executeUpdate();
			System.out.println("Data Updated Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int custId) {
		// TODO Auto-generated method stub

		String DELETEBYID_SQL = "delete from customer where custid=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETEBYID_SQL);
			preparedStatement.setInt(1, custId);
			preparedStatement.executeUpdate();

			System.out.println("Data Deleted from DB Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAllData() {
		// TODO Auto-generated method stub

		String DELETEALL_SQL = "truncate table customer";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETEALL_SQL);

			preparedStatement.executeUpdate();

			System.out.println("All Data Deleted Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Customer> sortByName() {
		// TODO Auto-generated method stub
		return getAllData().stream().sorted((c1, c2) -> c1.getCustName().compareTo(c2.getCustName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> sortByAge() {
		// TODO Auto-generated method stub
		return getAllData().stream().sorted(Comparator.comparingLong(Customer::getCustAge))
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> sortByAccountBalance() {
		// TODO Auto-generated method stub
		return getAllData().stream().sorted(Comparator.comparingDouble(Customer::getCustAccountBalance))
				.collect(Collectors.toList());
	}

	@Override
	public List<Customer> sortByDOB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> filterByAccpountBalance(double custAccpountBalance) {
		// TODO Auto-generated method stub
		return getAllData().stream().filter(cust -> cust.getCustAccountBalance() >= custAccpountBalance)
				.collect(Collectors.toList());
	}

}
