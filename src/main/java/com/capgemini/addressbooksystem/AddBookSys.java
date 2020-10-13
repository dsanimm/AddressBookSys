package com.capgemini.addressbooksystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.stream.Stream;

public class AddBookSys {
	public static String ADDRESS_BOOK_FILE_NAME;

	public static void main(String[] args) {

		System.out.println("Welcome to Address Book Program");
		Scanner sc = new Scanner(System.in);

		String firstName, lastName, city, email, name;
		int zip, choice = 1;
		long phoneNo;
		while (choice != 3) {
			System.out.println("Enter your choice:\n1. Create new Address Book\n2. Select AddressBook\n3. Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Name of AddressBook:");
				name = sc.next().concat(".txt");
				try {
					Files.createFile(Paths.get(name));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ADDRESS_BOOK_FILE_NAME = name;
				break;
			case 2:
				System.out.println("Enter Name of AddressBook:");
				name = sc.next();
				ADDRESS_BOOK_FILE_NAME = name;
				break;
			case 3:
				choice = 4;
			default:
				System.out.println("Wrong choice");
			}
			while (choice != 4) {
				System.out.println("Enter your choice:\n1. Enter contact\n2. Edit Contact\n3. Delete Contact\n4. Exit");

				choice = sc.nextInt();

				switch (choice) {
				case 1:
					System.out.println("Enter First Name:");
					firstName = sc.next();
					final String temp = firstName;
					Stream<String> s = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME)).map(l -> l.split(","))
							.map(l -> l[0].equals(temp) ? "record found" : "");
					boolean j = s.anyMatch(l -> l.equals("record found"));
					// boolean j = true;
					if (j == false) {
						System.out.println("Enter Last Name:");
						lastName = sc.next();
						System.out.println("Enter city:");
						city = sc.next();
						System.out.println("Enter zip:");
						zip = sc.nextInt();
						System.out.println("Enter phone No.:");
						phoneNo = sc.nextLong();
						System.out.println("Enter email address:");
						email = sc.next();
						AddressContact add = new AddressContact(firstName, lastName, city, zip, phoneNo, email);
						try {
							Files.write(Paths.get(ADDRESS_BOOK_FILE_NAME), add.toString().getBytes(),
									StandardOpenOption.APPEND);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else
						System.out.println("Record already present");
					break;
				case 2:
					System.out.println("Enter First Name:");
					firstName = sc.next();
					final String temp = firstName;
					try {
						Stream<String> s = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME)).map(l -> l.split(","))
								.map(l -> l[0].equals(temp) ? "record found" : "");
						// s.forEach(System.out::println);
						boolean j = s.anyMatch(l -> l.equals("record found"));
						// boolean j = true;
						if (j == true) {
							System.out.println("Enter Last Name:");
							lastName = sc.next();
							System.out.println("Enter city:");
							city = sc.next();
							System.out.println("Enter zip:");
							zip = sc.nextInt();
							System.out.println("Enter phone No.:");
							phoneNo = sc.nextLong();
							System.out.println("Enter email address:");
							email = sc.next();
							AddressContact add1 = new AddressContact(firstName, lastName, city, zip, phoneNo, email);
							Stream<String> s1 = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME))
									.map(l -> l.split(",")[0].equals(temp) ? add1.toString() : l);
							// Files.write(Paths.get(ADDRESS_BOOK_FILE_NAME), "".getBytes());
							Files.delete(Paths.get(ADDRESS_BOOK_FILE_NAME));
							try {
								Files.createFile(Paths.get(ADDRESS_BOOK_FILE_NAME));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							s1.forEach(l -> {
								try {
									Files.write(Paths.get(ADDRESS_BOOK_FILE_NAME), l.getBytes(),
											StandardOpenOption.APPEND);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});

						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				case 3:
					System.out.println("Enter First Name:");
					firstName = sc.next();
					final String temp1 = firstName;
					try {
						Stream<String> s = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME)).map(l -> l.split(","))
								.map(l -> l[0].equals(temp1) ? "record found" : "");
						// s.forEach(System.out::println);
						boolean j = s.anyMatch(l -> l.equals("record found"));
						// boolean j = true;
						if (j == true) {
							Stream<String> s1 = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME))
									.map(l -> l.split(",")[0].equals(temp1) ? "" : l);
							// Files.write(Paths.get(ADDRESS_BOOK_FILE_NAME), "".getBytes());
							Files.delete(Paths.get(ADDRESS_BOOK_FILE_NAME));
							try {
								Files.createFile(Paths.get(ADDRESS_BOOK_FILE_NAME));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							s1.forEach(l -> {
								try {
									Files.write(Paths.get(ADDRESS_BOOK_FILE_NAME), l.getBytes(),
											StandardOpenOption.APPEND);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});

						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case 4:
					break;
				default:
					System.out.println("Wrong choice");

				}
			}
		}
	}
}
