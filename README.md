# Project Foodhunter

#### Information

- Author: Philip Tonaczew
- Last update: February 9, 2023

Java-based web scraping application that facilitates the creation of a shopping list. The application automatically extracts the prices of each item in the list from two different stores and presents the user with the information on where to buy each item at the most affordable price. The application is designed to provide users with a simple and efficient means of comparing prices, enhancing their shopping experience.


## Installation

- Make sure you have at least Java 10 installed on your machine. 
- Install Spring Boot by following the instructions on the Spring Boot website.
- Download ChromeDriver version 113 from the ChromeDriver downloads page. Make sure to choose the appropriate version for your operating system.
- You must have the latest version of Google Chrome installed on your machine in order to use this project.
- Add the ChromeDriver executable to your system PATH. 
- Clone this repository to your local machine using the command 
`git clone https://github.com/tonaczew/project-foodhunter.git`.
- Install the project's dependencies using Maven by running the command `mvn install` in the project's root directory. This will download and install all of the dependencies specified in the pom.xml file.


## Usage

To use this project, you can interact with the simple front-end that is available on the [project-foodhunter-frontend](https://github.com/tonaczew/project-foodhunter-frontend) repository. Once you have navigated to the front-end, you can use the following steps to create a shopping list:

1. Start by adding items to the list by typing them in and pressing "Enter".
2. Continue adding items until you are satisfied with your list.
3. Once your list is complete, click the "Ready" button to mark it as complete.
4. Click the "Get Price" button to get the prices for the items on your list.
5. If you want to start over, you can click the "Clear" button to clear the page and start again.

That's it! Once you have completed these steps, you should have a list of items with their corresponding prices.

## Version 1.0
This is the first version of the Food Hunter project, which currently supports two stores:

- Hemköp
- Ica Maxi Lindhagen

In future versions of the project, we plan to expand the number of stores that are supported. However, for now, we hope that this limited selection will still be useful to you.

Thank you for trying out the Food Hunter project! If you have any feedback or suggestions, please don't hesitate to reach out to us.

## License

The Food Hunter project is released under the MIT License. For more information, please see the LICENSE.md file in the root directory of this repository.

By contributing to the Food Hunter project, you agree to license your contributions under the same license as the project itself. This ensures that the project remains open and freely available to the community.
