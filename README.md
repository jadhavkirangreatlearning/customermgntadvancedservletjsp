# customermgntadvancedservletjsp

-- create database CustomerManagement

-- use CustomerManagement



CREATE TABLE customer(custid INT, custname VARCHAR(255),
custaddress VARCHAR(255), custage INT, custcontactnumber BIGINT, 
custaccountbalance DOUBLE, custdob DATE,
custemailid VARCHAR(255), custpassword VARCHAR(255),
PRIMARY KEY(custid), UNIQUE KEY(custcontactnumber),
UNIQUE KEY(custemailid))

DESC customer

SELECT * FROM customer


------------------------------------


J2EE- Java | Servlet | JSP | MySQL

Functionality->

SignUp | SignIn | Show | Update | Delete | Delete All Data

Sort By Name | Sort By Age | Sort By accBalance | Sort By DOB

Filter Data By AccountBalance

CustomerManagement

MVC-
M- model[DAO]
V- view[Presentation]
C- controller[Business]

Back End- Java
model- Customer.java[POJO]
dao- CustomerDao.java[Interface]
     CustomerDaoImpl.java[Implementation Class]
	 
service- CustomerService.java[Interface]
         CustomerServiceImpl.java[Implementation Class]
		 
controller- CustomerController.java[Servlet]

Front End- [UI- User Interface]
index.jsp
signup.jsp
signin.jsp
show.jsp
edit.jsp

------------------
Database

CustomerManagement

customer

custid custname custaddress custage custcontactnumber 
custaccountbalance custdob custemailid custpassword


