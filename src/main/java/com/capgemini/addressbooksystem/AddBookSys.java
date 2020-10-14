package com.capgemini.addressbooksystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddBookSys {
	public static String ADDRESS_BOOK_FILE_NAME;
	public static final String SAMPLE_CSV_FILE_PATH = "users.csv";
	private static final String SAMPLE_JSON_FILE_PATH = "users.json";

	public static void main(String[] args) {

		System.out.println("Welcome to Address Book Program");
		Scanner sc = new Scanner(System.in);

		String firstName, lastName, city, email, name;
		int zip, choice = 1;
		long phoneNo;
		while (choice != 4) {
			System.out.println(
					"Enter your choice:\n1. Create new Address Book\n2. Select AddressBook\n3. Search Person\n4. View person by city\n5. View Count by city\n6.Exit");
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
				name = sc.next().concat(".txt");
				;
				ADDRESS_BOOK_FILE_NAME = name;
				break;
			case 3:
				System.out.println("Enter Name");
				name = sc.next();
				System.out.println("Enter City");
				city = sc.next();
				String tempb = name;
				String tempa = city;
				List<Path> lis = null;
				try {
					lis = Files.list(Paths.get("C:\\Users\\Deepanshu Singh\\eclipse-workspace\\JavaPractice"))
							.filter(path -> path.toString().endsWith(".txt")).collect(Collectors.toList());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Stream<String> s = null;
				for (Path pat : lis) {
					try {
						s = Files.lines(Paths.get(pat.toUri()))
								.map(l -> l.split(",")[0].equals(tempb) && l.split(",")[2].equals(tempa) ? l : "");
						s.forEach(System.out::print);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				choice = 4;

				break;
			case 4:
				System.out.println("Enter City");
				city = sc.next();
				tempa = city;
				lis = null;
				try {
					lis = Files.list(Paths.get("C:\\Users\\Deepanshu Singh\\eclipse-workspace\\JavaPractice"))
							.filter(path -> path.toString().endsWith(".txt")).collect(Collectors.toList());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				s = null;
				for (Path pat : lis) {
					try {
						s = Files.lines(Paths.get(pat.toUri())).map(l -> l.split(",")[2].equals(tempa) ? l : "");
						s.forEach(System.out::print);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				choice = 4;

				break;
			case 5:
				System.out.println("Enter City");
				city = sc.next();
				tempa = city;
				lis = null;
				try {
					lis = Files.list(Paths.get("C:\\Users\\Deepanshu Singh\\eclipse-workspace\\JavaPractice"))
							.filter(path -> path.toString().endsWith(".txt")).collect(Collectors.toList());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				long d = 0;
				for (Path pat : lis) {
					try {
						d = Files.lines(Paths.get(pat.toUri())).map(l -> l.split(",")[2].equals(tempa) ? l : "")
								.count();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(d);
				break;
			case 6:
				choice = 4;
				break;
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
					Stream<String> s = null;
					try {
						s = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME)).map(l -> l.split(","))
								.map(l -> l[0].equals(temp) ? "record found" : "");
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					boolean j = s == null ? true : s.anyMatch(l -> l.equals("record found"));
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
			
						try (Writer writer1 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));) {
							StatefulBeanToCsv<AddressContact> beanToCsv = new StatefulBeanToCsvBuilder(writer1)
									.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

							
							beanToCsv.write(add);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							Gson gson =new Gson();
							String json =gson.toJson(add);
							FileWriter writer = new FileWriter(SAMPLE_JSON_FILE_PATH);
							writer.write(json);
							writer.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						
						String[] nextRecord;
						
						Reader reader = null;
						try {
							reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						CSVReader csvReader = new CSVReader(reader);
						try {
							nextRecord = csvReader.readNext();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							while ((nextRecord = csvReader.readNext()) != null) {
								firstName = nextRecord[2];
								lastName = nextRecord[3];
								city = nextRecord[0];
								zip = Integer.parseInt(nextRecord[5]);
								phoneNo = Integer.parseInt(nextRecord[4]);
								email = nextRecord[1];
								AddressContact add1 = new AddressContact(firstName, lastName, city, zip, phoneNo, email);
								System.out.println(add1);
								BufferedReader br = new BufferedReader(new FileReader(SAMPLE_JSON_FILE_PATH));
								Gson gson =new Gson();
								AddressContact addListJson = gson.fromJson(br, AddressContact.class);
								List<AddressContact> addList=Arrays.asList(addListJson);
								addList.forEach(System.out::println);
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
					final String temp1 = firstName;
					try {
						s = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME)).map(l -> l.split(","))
								.map(l -> l[0].equals(temp1) ? "record found" : "");
						// s.forEach(System.out::println);
						j = s.anyMatch(l -> l.equals("record found"));
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
									.map(l -> l.split(",")[0].equals(temp1) ? add1.toString() : l);
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
					final String temp11 = firstName;
					try {
						s = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME)).map(l -> l.split(","))
								.map(l -> l[0].equals(temp11) ? "record found" : "");
						// s.forEach(System.out::println);
						j = s.anyMatch(l -> l.equals("record found"));
						// boolean j = true;
						if (j == true) {
							Stream<String> s1 = Files.lines(Paths.get(ADDRESS_BOOK_FILE_NAME))
									.map(l -> l.split(",")[0].equals(temp11) ? "" : l);
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
