package project;

import java.util.ArrayList;
import java.util.List;


public class ProjectTest {
	private Project project;
	String[] users;
	String[] passwords;
	boolean[] userTypes;
	String[] cities;
	String[] warehouses;
	String[][] edges;
	int[] weights;
	String[] categories;
	int[] catIDs;
	String[] products;
	int[] prodIDs;
	int[][] prodQtys;
	String[] customers;
	String[] streets;
	String[] custCities;
	int[] custIDs;

	
	public void setUp() throws Exception {
		project = new Project();
		users = new String[] { "Shopper1", "Shopper2", "Admin1", "Admin2" };
		passwords = new String[] { "pass1", "pass2", "pass3", "pass4" };
		userTypes = new boolean[] { false, false, true, true };
		cities = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J" };
		warehouses = new String[] { "A", "E", "I" };
		edges = new String[][] { { "A", "B" }, { "A", "C" }, { "A", "D" },
				{ "B", "D" }, { "B", "E" }, { "C", "D" }, { "C", "F" },
				{ "D", "E" }, { "D", "G" }, { "E", "H" }, { "F", "G" },
				{ "F", "I" }, { "G", "H" }, { "G", "I" }, { "G", "J" },
				{ "H", "J" }, { "I", "J" } };
		weights = new int[] { 20, 9, 12, 11, 13, 2, 10, 4, 17, 6, 7, 8, 16, 5,
				18, 2, 21 };
		categories = new String[] { "TSHIRT", "BOOK" };
		products = new String[] { "Blue T-Shirt L", "Grey T-Shirt S",
				"Red T-Shirt XL", "Introduction to Java" };
		prodQtys = new int[][] { { 100, 200, 300 }, { 50, 50, 50 },
				{ 300, 200, 100 }, { 1000, 2000, 3000 } };
		customers = new String[] { "Customer B", "Customer J" };
		streets = new String[] { "123 Bloor St", "123 Yonge St" };
		custCities = new String[] { "B", "J" };
		catIDs = new int[2];
		prodIDs = new int[4];
		custIDs = new int[2];
	}

	
	public void tearDown() throws Exception {
		// Not implemented
	}


	public void testAll() {
		test1AddUser();
		test2AddUser();
		test3AddUser();
		testLoginLogout();
		test1AddCategory();
		test2AddCategory();
		test3AddCategory();
		testAddDistributionCenter();
		test1AddCustomer();
		test2AddCustomer();
		test3AddCustomer();
		testAddRoute();
		test1AddProduct();
		test2AddProduct();
		test1ProdInquiry();
		test2ProdInquiry();
		test1UpdateQuantity();
		test2UpdateQuantity();
		test3UpdateQuantity();
                test3ProdInquiry();
		test1PlaceOrder();
		test2PlaceOrder();
		test3PlaceOrder();
		test4PlaceOrder();
		test5PlaceOrder();
	}

	public void test1AddUser() {
		String result = "Testing addUser shopper1 ... ";
		try {
			boolean b = project.addUser(users[0], passwords[0], userTypes[0]);
			if (!b) {
				result = "-" + result + " unable to add new user";
				System.out.println(result);
			} else {
				result += "ok!";
				System.out.println(result);
			}

		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test2AddUser() {
		String result = "Testing addUser shopper1 duplicate ... ";
		try {
			boolean b = project.addUser(users[0], passwords[0], userTypes[0]);
			if (b) {
				result = "-" + result + " duplicate user added";
				System.out.println(result);
			} else {
				result += "ok!";
				System.out.println(result);
			}

		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test3AddUser() {
		String result = "Adding all users  ... ";
		try {

			int errCtr = 0;
			for (int i = 1; i < users.length; i++) {
				boolean b = project.addUser(users[i], passwords[i],
						userTypes[i]);
				if (!b) {
					errCtr++;
					System.out.println("Unable to add " + users[i]);
				}
			}
			if (errCtr > 0) {
				result = "-" + result + " unable to add all users!";
				System.out.println(result);
			} else {
				result += "ok!";
				System.out.println(result);
			}

		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void testLoginLogout() {
		String result = "Testing login for all users ... ";
		try {
			int errCtr = 0;
			for (int i = 1; i < users.length; i++) {
				int sessionID = project.login(users[i], passwords[i]);
				if (sessionID == -1)
					errCtr++;
				else
					project.logout(sessionID);
			}
			if (errCtr > 0) {
				result = "-" + result + " unable to log in all users!";
				System.out.println(result);
			} else {
				result += "ok!";
				System.out.println(result);
			}

		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test1AddCategory() {
		String result = "Testing addCategory for a shopper ... ";
		try {
			int sessionID = project.login(users[0], passwords[0]);
			int catID = 9999;
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[0];
				System.out.println(result);
			} else
				catID = project.addCategory("TSHIRT", sessionID);
			project.logout(sessionID);
			if (catID == -1) {
				result += "ok!";
				System.out.println(result);
			} else {
				result = "-" + result + "failed!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test2AddCategory() {
		String result = "Testing addCategory TSHIRT for admins ... ";
		try {
			int sessionID = project.login(users[2], passwords[2]);
			int catID = 0;
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {
				catID = project.addCategory("TSHIRT", sessionID);
				catIDs[0] = catID;
                                project.logout(sessionID);
                                result += "ok!";
                                System.out.println(result);
			}

		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test3AddCategory() {
		String result = "Testing addCategory BOOK for admins ... ";
		try {
			int sessionID = project.login(users[3], passwords[3]);
			int catID = 0;
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[3];
				System.out.println(result);
			} else {
				catID = project.addCategory("BOOK", sessionID);
				catIDs[1] = catID;
                                project.logout(sessionID);
                                result += "ok!";
                                System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void testAddDistributionCenter() {
		String result = "Testing addDistributionCenter  ... ";
		try {
			int sessionID = project.login(users[2], passwords[2]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {
				for (String c : cities)
					project.addDistributionCenter(c, sessionID);
				project.logout(sessionID);
				result += "ok!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test1AddCustomer() {
		String result = "Testing addCustomer for " + users[0] + " ... ";
		try {
			int sessionID = project.login(users[0], passwords[0]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[0];
				System.out.println(result);
			} else {
				custIDs[0] = project.addCustomer(customers[0], custCities[0],
						streets[0], sessionID);
				project.logout(sessionID);
				result += "ok!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test2AddCustomer() {
		String result = "Testing addCustomer for duplicates: adding "
				+ users[0] + " again ... ";
		int custID = 0;
		try {
			int sessionID = project.login(users[0], passwords[0]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[0];
				System.out.println(result);
			} else
				custID = project.addCustomer(customers[0], custCities[0],
						streets[0], sessionID);
			project.logout(sessionID);
			if (custID == custIDs[0] || custID == -1) {
				result += "ok!";
				System.out.println(result);
			} else {
				result = "-" + result + "failed!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test3AddCustomer() {
		String result = "Testing addCustomer  ... ";
		try {
			int sessionID = project.login(users[1], passwords[1]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[1];
				System.out.println(result);
			} else {
				custIDs[0] = project.addCustomer(customers[1], custCities[1],
						streets[1], sessionID);
				project.logout(sessionID);
				result += "ok!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void testAddRoute() {
		String result = "Testing addRoute for admin ... ";
		try {
			int sessionID = project.login(users[2], passwords[2]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {
				for (int i = 0; i < edges.length; i++)
				{
					project.addRoute(edges[i][0], edges[i][1], 100 * weights[i], sessionID);
				}
				project.logout(sessionID);
				result += "ok!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test1AddProduct() {
		String result = "Testing addProduct for a shopper ... ";
		try {
			int sessionID = project.login(users[0], passwords[0]);
			int prodID = 0;
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[0];
				System.out.println(result);
			} else
				prodID = project.addProduct(products[3], " ", " ", 0, catIDs[1], 1.0,
						sessionID);
			project.logout(sessionID);
			if (prodID == -1) {
				result += "ok!";
				System.out.println(result);
			} else {
				result = "-" + result + "failed!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test2AddProduct() {
		String result = "Test adding all products ... ";
		try {
			int sessionID = project.login(users[2], passwords[2]);
			int prodID = 0;
			int errCtr = 0;
			int catID = 0;
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {
				for (int i = 0; i < products.length; i++) {
					if (i < 3)
						catID = catIDs[0];
					else
						catID = catIDs[1];
					prodID = project.addProduct(products[i], " ", " ", 0, catID, 1.0,sessionID);
					if (prodID == -1)
						errCtr++;
					else
						prodIDs[i] = prodID;
				}
			}
			project.logout(sessionID);
			if (errCtr == 0) {
				result += "ok!";
				System.out.println(result);
			} else {
				result = "-" + result + "failed!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test1ProdInquiry() {
		String result = "Testing prodInquiry prodID=9999 ... ";
		int test = 9999;
		try {
			int qty = project.prodInquiry(test, warehouses[0]); // expect -1
			if (qty == -1) {
				result += "ok!";
				System.out.println(result);
			} else {
				result = "-" + result + "failed!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test2ProdInquiry() {
		String result = "Testing prodInquiry non existent center X ... ";
		try {
			int qty = project.prodInquiry(prodIDs[0], "X"); // expect -1
			if (qty == -1) {
				result += "ok!";
				System.out.println(result);
			} else {
				result = "-" + result + "failed!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test1UpdateQuantity() {
		String result = "Testing updateQuantity for prodID=9999 ... ";
		int sessionID = -1;
		try {
			sessionID = project.login(users[2], passwords[2]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {

				int test = 9999;
				boolean r = project.updateQuantity(test, "A", 70, sessionID); // expect
																				// false
				project.logout(sessionID);
				if (!r) {
					result += "ok!";
					System.out.println(result);
				} else {
					result = "-" + result + "failed!";
					System.out.println(result);
				}

			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test2UpdateQuantity() {
		String result = "Testing updateQuantity " + "prodID=" + prodIDs[0]
				+ " center X ... ";
		try {
			int sessionID = project.login(users[2], passwords[2]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {
				boolean r = project.updateQuantity(prodIDs[0], "X", 70,
						sessionID); // expect false // false
				project.logout(sessionID);
				if (!r) {
					result += "ok!";
					System.out.println(result);
				} else {
					result = "-" + result + "failed!";
					System.out.println(result);
				}

			}
		} catch (Exception e) {
			result = "-" + result + "failed, exeption:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test3UpdateQuantity() {
		String result = "Testing updateQuantity ... ";
		int ctrErr = 0;
		boolean r;
		try {
			int sessionID = project.login(users[2], passwords[2]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {
				for (int i = 0; i < prodIDs.length; i++)
					for (int j = 0; j < prodQtys[i].length; j++) {
						System.out
								.println("Testing update quantity, trying to add "
										+ prodQtys[i][j] + " units of prodID= "
										+ prodIDs[i] + " to warehouse "
										+ warehouses[j]);
						r = project.updateQuantity(prodIDs[i], warehouses[j],
								prodQtys[i][j], sessionID); // expect true
						if (!r) {
							System.out.println("-Unable to add "
									+ prodQtys[i][j] + " units of "
									+ prodIDs[i] + " to warehouse "
									+ warehouses[j]);
							ctrErr++;
						}
					}
				project.logout(sessionID);
				if (ctrErr == 0) {
					result += "ok!";
					System.out.println(result);
				} else {
					result = "-" + result + "failed!";
					System.out.println(result);
				}
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test3ProdInquiry() {
		String result = "Testing prodInquiry prodID=" + prodIDs[0]
				+ " warehouse A expected 100 ... got ";
		try {
			int qty = project.prodInquiry(prodIDs[0], "A"); // expect 100
			if (qty == 100) {
				result += "100, ok!";
				System.out.println(result);
			} else {
				result = "-" + result + qty + ", failed!";
				System.out.println(result);
			}
		} catch (Exception e) {
			result = "-" + result + "failed, exception:";
			System.out.println(result);
			e.printStackTrace();
		}
	}

	public void test1PlaceOrder() {
		String result = "Testing placeOrder, shopper=B, Prod="+prodIDs[0]+", qty = 100 ... ";
		int orderID = -1;
		List<String> expectedRoute = new ArrayList<String>();
		expectedRoute.add("E");
		expectedRoute.add("B");
                List<String> expectedRoute1 = new ArrayList<String>();
                expectedRoute1.add("B");
                expectedRoute1.add("E");
		List<String> route = new ArrayList<String>();
		int prodQty = 0;
		double invoiceAmt = 0.0;
		try {
			int sessionID = project.login(users[0], passwords[0]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[0];
				System.out.println(result);
			} else {
				orderID = project.placeOrder(custIDs[0], prodIDs[0], 100,
						sessionID);
				prodQty = project.prodInquiry(prodIDs[0], "E");
				route = project.getDeliveryRoute(orderID, sessionID);
				invoiceAmt = project.invoiceAmount(orderID, sessionID);
				if (orderID == -1)
					System.out.println("-" + result + " failed!");
				else
					System.out.println(result + " ok!");
				if (prodQty != 100)
					System.out.println("-" + result + " inventory balance "
							+ prodQty + ", expected 100");
				else
					System.out.println(result + " inventory balance ok!");
				if (invoiceAmt <= 100.0)
					System.out.println("-" + result
							+ " no shipping calculated!");
				else
					System.out.println(result + " shipping computed ok!");
				if (!(expectedRoute.equals(route)||expectedRoute1.equals(route)))
					System.out.println("-" + result + " route " + route
							+ ", expected [E,B]");
				else
					System.out.println(result + " route ok!");
				project.logout(sessionID);
			}

		} catch (Exception e) {
			String r1 = "-" + result + "orderID test failed, exception:";
			System.out.println(r1); // orderID
			e.printStackTrace();
			String r2 = "-" + result
					+ "inventory balance test failed, exception:";
			System.out.println(r2); // qty
			e.printStackTrace();
			String r3 = "-" + result + "invoice test failed, exception:";
			System.out.println(r3); // amt
			e.printStackTrace();
			String r4 = "-" + result + "route test failed, exception:";
			System.out.println(r4); // route
			e.printStackTrace();
		}
	}

	public void test2PlaceOrder() {
		String result = "Testing placeOrder, shopper=B, Prod=4, qty = 4000 ... ";
		int orderID = -1;
		int prodQty = 0;
		try {
			int sessionID = project.login(users[0], passwords[0]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[0];
				System.out.println(result);
			} else {
				orderID = project.placeOrder(custIDs[0], prodIDs[3], 4000,
						sessionID);
				prodQty = project.prodInquiry(prodIDs[3], "A");
				prodQty += project.prodInquiry(prodIDs[3], "E");
				prodQty += project.prodInquiry(prodIDs[3], "I");
				if (orderID != -1)
					System.out.println("-" + result + " orderID = " + orderID
							+ ", expected -1");
				else
					System.out.println(result + " orderID ok!");
				if (prodQty != 6000)
					System.out.println("-" + result + " inventory balance "
							+ prodQty + ", expected 6000");
				else
					System.out.println(result + " inventory balance ok!");
				project.logout(sessionID);
			}

		} catch (Exception e) {
			String r1 = "-" + result + "orderID test failed, exception:";
			System.out.println(r1); // orderID
			e.printStackTrace();
			String r2 = "-" + result
					+ "inventory balance test failed, exception:";
			System.out.println(r2); // qty
			e.printStackTrace();
		}
	}

	public void test3PlaceOrder() {
		String result = "Testing placeOrder, shopper=J, Prod="+prodIDs[2]+", qty = 195 ... ";
		int orderID = -1;
		List<String> expectedRoute = new ArrayList<String>();
		expectedRoute.add("E");
		expectedRoute.add("H");
		expectedRoute.add("J");
                List<String> expectedRoute1 = new ArrayList<String>();
                expectedRoute1.add("J");
                expectedRoute1.add("H");
                expectedRoute1.add("E");
		List<String> route = new ArrayList<String>();
		int prodQty = 5;
		double invoiceAmt = 0.0;
		try {
			int sessionID = project.login(users[1], passwords[1]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[1];
				System.out.println(result);
			} else {
				orderID = project.placeOrder(custIDs[1], prodIDs[2], 195,
						sessionID);
				prodQty = project.prodInquiry(prodIDs[2], "E");
				route = project.getDeliveryRoute(orderID, sessionID);
				invoiceAmt = project.invoiceAmount(orderID, sessionID);
				if (orderID == -1)
					System.out.println("-" + result + " failed!");
				else
					System.out.println(result + " ok!");
				if (prodQty != 5)
					System.out.println("-" + result + " inventory balance "
							+ prodQty + ", expected 5");
				else
					System.out.println(result + " inventory balance ok!");
				if (invoiceAmt <= 195.0)
					System.out.println("-" + result
							+ " no shipping calculated!");
				else
					System.out.println(" shipping computed ok!");
				if (!(expectedRoute.equals(route)||expectedRoute1.equals(route)))
					System.out.println("-" + result + " route " + route
							+ ", expected [E,H,J]");
				else
					System.out.println(result + "route ok!");
				project.logout(sessionID);
			}

		} catch (Exception e) {
			String r1 = "-" + result + "orderID test failed, exception:";
			System.out.println(r1); // orderID
			e.printStackTrace();
			String r2 = "-" + result
					+ "inventory balance test failed, exception:";
			System.out.println(r2); // qty
			e.printStackTrace();
			String r3 = "-" + result + "invoice test failed, exception:";
			System.out.println(r3); // amt
			e.printStackTrace();
			String r4 = "-" + result + "route test failed, exception:";
			System.out.println(r4); // route
			e.printStackTrace();
		}
	}

	public void test4PlaceOrder() {
		String result = "Testing placeOrder, user not logged in ... ";
		int orderID = -1;
		try {
			int sessionID = 12345;
			orderID = project
					.placeOrder(custIDs[1], prodIDs[1], 195, sessionID);
			if (orderID != -1)
				System.out.println("-" + result + " orderID = " + orderID
						+ ", expected -1");
			else
				System.out.println(result + " ok!");
		} catch (Exception e) {
			String r1 = "-" + result + "test failed, exception:";
			System.out.println(r1);
			e.printStackTrace();
		}
	}

	public void test5PlaceOrder() {
		String result = "Testing placeOrder, user admin ... ";
		int orderID = -1;
		try {
			int sessionID = project.login(users[2], passwords[2]);
			if (sessionID == -1) {
				result = "-" + result + " unable to log in " + users[2];
				System.out.println(result);
			} else {
				orderID = project.placeOrder(custIDs[1], prodIDs[1], 195,
						sessionID);
				if (orderID != -1)
					System.out.println("-" + result + " orderID = " + orderID
							+ ", expected -1");
				else
					System.out.println(result + "  ok!");
			}
			project.logout(sessionID);

		} catch (Exception e) {
			String r1 = "-" + result + " test failed, exception:";
			System.out.println(r1);
			e.printStackTrace();
		}
	}

}


