# Vending-Machine-Kata
Mobile Vending Machine App Kata
===============================
In this exercise you will build a simulated vending machine mobile application.
It will accept money, make change, maintain inventory, and dispense products.
All the things that you might expect a vending machine to accomplish.

Mobile Requirements
===================
The mobile interface should consist of one or more screens with appropriate controls to expose the vending machine functionality.
The app should work whether it is online or offline, and no external data (remote services, etc) are required.
No special consideration should be made for screen orientation.

Features
========

Accept Coins
------------
Story: As a vendor
I want a vending machine that accepts coins
So that I can collect money from the customer

Description: The vending machine will accept valid coins (nickels, dimes, and quarters) and reject invalid ones (pennies).
When a valid coin is inserted the amount of the coin will be added to the current amount and the display will be updated.
When there are no coins inserted, the machine displays INSERT COIN.
Rejected coins are placed in the coin return.
In order to simulate inserting coins and rejecting coins the code should accept user input which is not strictly limited to the valid coins.

NOTE: An object-oriented temptation here will be to create Coin objects that know their values and to use them in the application.
Avoid this urge as it is not reflective of how vending machines work.
Instead, use strings, enums, constants, or something of that nature to represent coins.

Select Product
--------------
Story: As a vendor
I want customers to select products
So that I can give them an incentive to put money in the machine

Description: There are three products: cola for $1.00, chips for $0.50, and candy for $0.65.
When the respective button is pressed and enough money has been inserted, the product is dispensed and the machine displays THANK YOU.
If the display is checked again, it will display INSERT COIN and the current amount will be set to $0.00.
If there is not enough money inserted then the machine displays PRICE and the price of the item and subsequent checks of the display will display either INSERT COIN or the current amount as appropriate.

Make Change
-----------
Story: As a vendor
I want customers to receive correct change
So that they will use the vending machine again

Description: When a product is selected that costs less than the amount of money in the machine, then the remaining amount is placed in the coin return.

Return Coins
------------
Story: As a customer
I want to have my money returned
So that I can change my mind about buying stuff from the vending machine

Description: When the return coins button is pressed, the money the customer has placed in the machine is returned and the display shows INSERT COIN.

Sold Out
--------
Story: As a customer
I want to be told when the item I have selected is not available
So that I can select another item

Description: When the item selected by the customer is out of stock, the machine displays SOLD OUT.
If the display is checked again, it will display the amount of money remaining in the machine or INSERT COIN if there is no money in the machine.

Exact Change Only
-----------------
Story: As a customer
I want to be told when exact change is required
So that I can determine if I can buy something with the money I have before inserting it

Description: When the machine is not able to make change with the money in the machine for any of the items that it sells, it will display EXACT CHANGE ONLY instead of INSERT COIN.
