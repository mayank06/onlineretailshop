# onlineretailshop
online shopping cart

It is a spring-maven-mysql java/jee project for check out counter for an online retail store that scans products and generates an itemized bill.

For database configuration in the project :

Go to  :   /onlineshoppingcart/src/main/webapp/WEB-INF/
And open onlineretailshop-servlet.xml file to configure database details.

<bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/mypractice" />
		<property name="username" value="root" />
		<property name="password" value="password" />
	</bean>

Change the schema name and user/password details.



+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

URLs for RESTful APIs :

1.	To  get the Categories
URL : http://localhost:8080/onlineshoppingcart/categories
Method : GET
JSON Response :

[
  {
    "id": 1,
    "category": "Groceries & Staples"
  },
  {
    "id": 2,
    "category": "Beverages"
  },
  {
    "id": 3,
    "category": "Personal Care"
  }
]

2.	To get all Products in a Category 
URL : http://localhost:8080/onlineshoppingcart/products/{categoryId}
Ex : http://localhost:8080/onlineshoppingcart/products/1
Method : GET
JSON Response :

[
  {
    "productId": 1,
    "productName": "Basmati Rice",
    "categoryId": 1,
    "description": "Kohinoor Basmati Rice",
    "unit": null,
    "priceValue": 145.5
  },
  {
    "productId": 4,
    "productName": "Sunflower Oil",
    "categoryId": 1,
    "description": "Sunflower Low cholestrol oil",
    "unit": null,
    "priceValue": 110.6
  }
]

3.	To get details of a Product
URL : http://localhost:8080/onlineshoppingcart/details/{productId}
Ex : http://localhost:8080/onlineshoppingcart/details/3
Method : GET
JSON Response :

{
  "productId": 3,
  "productName": "Nivea Men Face Wash",
  "categoryId": 3,
  "description": "Oil resistant Face Wash for Men",
  "unit": null,
  "priceValue": 115
}

4.	Checkout from My Cart
URL : http://localhost:8080/onlineshoppingcart/checkout/{customerId}
Ex : http://localhost:8080/onlineshoppingcart/checkout/1
Method : GET
JSON Response :

{
  "lineItems": [
    {
      "productId": 1,
      "productName": "Basmati Rice",
      "productDesc": "Kohinoor Basmati Rice",
      "quantity": 1,
      "price": 145.5,
      "taxValue": 10,
      "categoryId": 1,
      "category": "Groceries & Staples",
      "amount": 160.05, 
      "unitOfItem": "1Kg"
    },
    {
      "productId": 2,
      "productName": "Diet Coke",
      "productDesc": "Cold Drinks Cocacola Family Pack",
      "quantity": 2,
      "price": 55,
      "taxValue": 20,
      "categoryId": 2,
      "category": "Beverages",
      "amount": 132,
      "unitOfItem": "1L"
    },
    {
      "productId": 3,
      "productName": "Nivea Men Face Wash",
      "productDesc": "Oil resistant Face Wash for Men",
      "quantity": 2,
      "price": 115,
      "taxValue": 0,
      "categoryId": 3,
      "category": "Personal Care",
      "amount": 230,
      "unitOfItem": "100gms"
    }
  ],
  "customer": {
    "customerId": 1,
    "customerName": "Mayank Sinha",
    "email": "mayank06sinha@gmail.com",
    "address": "pune, india"
  }
}

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Docs and code also present in Dropbox : https://www.dropbox.com/sh/neegjv2lpeys0m7/AAAOZyNN-sr-kMtL4I70xrYwa?dl=0

