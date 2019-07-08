/**
 * Copyright (c) 2019.
 * This program and the accompanying materials are made available
 * under my granted permission provided that this note is kept intact, unmodified and unchanged.
 * @ Author: Baraa Ali -  API and implementation.
 * All rights reserved.
*/
/**
 * 
 */
package co.gccollections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingList {
	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		HashMap<String, Double> menu = new HashMap<String, Double>();
		ArrayList<String> itemsOrdered = new ArrayList<>();
		ArrayList<Double> PricesOfItemsOrdered = new ArrayList<>();
		ArrayList<Integer> quantitiesOfItemsOrdered = new ArrayList<>();

		menu = buildMenu();
		displayMenu(menu);
		getUserInput(menu, itemsOrdered, PricesOfItemsOrdered, quantitiesOfItemsOrdered, scnr);
		processOrders(itemsOrdered, PricesOfItemsOrdered, quantitiesOfItemsOrdered, scnr);
	}

	/*--------------------------------------------------------------------------*/
	public static HashMap<String, Double> buildMenu() {

		HashMap<String, Double> menu = new HashMap<>();
		menu.put("apple", 0.99);
		menu.put("banana", 0.59);
		menu.put("cauliflower", 1.59);
		menu.put("dragonfruit", 2.19);
		menu.put("Elderberry", 1.79);
		menu.put("figs", 2.09);
		menu.put("grapefruit", 1.99);
		menu.put("honeydew", 3.49);
		return menu;
	}

	public static void displayMenu(HashMap<String, Double> menu) {
		System.out.println("\t\t" + "Welcome to Guenther's Market!");
		System.out.println("Item" + "\t\t\t" + "Price");
		System.out.println("==============================");
		for (Map.Entry<String, Double> keyValuePair : menu.entrySet()) {
			System.out.printf("%-15s  %7s %1s %n", keyValuePair.getKey(), "$", keyValuePair.getValue());
		}
	}

	/*--------------------------------------------------------------------------*/
	public static void getUserInput(HashMap<String, Double> menu, ArrayList<String> itemsOrdered,
			ArrayList<Double> PricesOfItemsOrdered, ArrayList<Integer> quantitiesOfItemsOrdered, Scanner scnr) {
		String itemOrdered;
		int orderedItemCOunter = 0;
		String userWantToShop = "yes";
		int numberItems;

		do {
			System.out.println("What item would you like to order?");
			itemOrdered = scnr.nextLine();
			while (!checkItemExists(itemOrdered, menu)) {
				System.out.println("Sorry, we don't have those. Please try again");
				displayMenu(menu);
				System.out.println("What item would you like to order?");
				itemOrdered = scnr.nextLine();
			}

			itemsOrdered.add(itemOrdered);
			PricesOfItemsOrdered.add(menu.get(itemOrdered));
			System.out.println("Adding " + itemOrdered + " to cart at " + PricesOfItemsOrdered.get(orderedItemCOunter));
			orderedItemCOunter++;
			System.out.println("How many:");
			numberItems = scnr.nextInt();
			quantitiesOfItemsOrdered.add(numberItems);
			System.out.println("Would you like to order anything else (yes/no)");
			scnr.nextLine(); // clear out the Scanner
			userWantToShop = scnr.nextLine();
		} while (userWantToShop.equals("yes"));
		System.out.println("\nThanks for your order!");
	}

	/*--------------------------------------------------------------------------*/
	public static boolean checkItemExists(String userItem, HashMap<String, Double> menu) {
		if (menu.containsKey(userItem)) {
			return true;
		}
		return false;
	}

	/*--------------------------------------------------------------------------*/
	public static void processOrders(ArrayList<String> itemsOrdered, ArrayList<Double> PricesOfItemsOrdered,
			ArrayList<Integer> quantitiesOfItemsOrdered, Scanner scnr) {
		double totalPrice = 0;
		int totalNumItems = 0;
		double weightedAvarage = 0;
		System.out.println("Here's what you got:");
		for (int item = 0; item < itemsOrdered.size(); item++) {
			System.out.printf("%-15s Quantity:  %15s %15s %n", itemsOrdered.get(item),
					quantitiesOfItemsOrdered.get(item), PricesOfItemsOrdered.get(item));
			totalPrice = totalPrice + PricesOfItemsOrdered.get(item) * quantitiesOfItemsOrdered.get(item);
			totalNumItems = totalNumItems + quantitiesOfItemsOrdered.get(item);
		}
		weightedAvarage = totalPrice / totalNumItems;
		System.out.println("Weighted average price per item in order was $" + weightedAvarage);
	}
}
