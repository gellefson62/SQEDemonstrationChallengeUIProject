## Demo Challenge

#### Instructions
1. Complete the project setup as listed below
2. Complete the Excerise
3. Email a synopsis of your work and the link to your git repo containing the completed exercise to: sqedemonstrationchallenge@nbcuni.com

#### Technologies
1. Java
2. Selenium
3. TestNG
4. Any other technologies you see fit.
5. Please do not use a BDD framework.

#### Project Setup
1. Clone this project to your git account in a public repo
2. Setup the project in your IDE
3. Open the index.html file from src/test/resource/files in a browser
4. Copy the url from the browser and update the url value in src/test/resource/config.properties to be the copied url.
5. In src/test/resources update the config.properties file platform for your OS.
6. From command line run mvn clean install -U -DskipTests
7. Make sure you can run the DemoTest and chrome launches.  You may need to update the chromedriver in /src/test/resources/chromedriver/ to the version that works with your browser
   https://chromedriver.chromium.org/

#### Expectations
We will be evaluating
1. Quality of test cases
2. Variety  of testing types (examples: boundary, happy path, negative, etc)
3. Code structure and organization
4. Naming conventions
5. Code readability
6. Code modularity

#### Exercise
1. Use the site at the index.html
2. There are helper locators provided for you in the src/test/resource/files/locators.txt file.
3. In the Test Cases section below:
  - List all of the test cases you think are necessary to test the sample page
  - Note any defects or issues observed
4. Code up a few examples of:
  - At least one happy path case placing an order
  - At least one error case
5. When complete please check your code into your public git repo

#### Test Cases

 1. Exercise all Pizza types with "Happy path" cases. 
Choose Small no toppings pizza, do not select toppings, enter 1 for Quantity, enter name, email and Phone. Select Credit Card and click Place Order button.
Confirm popup with correct price for that pizza.  Repeat for each pizza, selecting toppings correctly as appropriate, changing quantity to different valid numbers.

2. choose pizza with 1 topping, do not select a topping. Enter 2 for Quantity, enter name and phone, select Cash and click Place Order button.
Confirm popup asks user to Please select a topping, retaining information already entered on form.  (Currently this popup asking for Topping is not implemented)

3. Error checking - enter all valid values EXCEPT Quantity, and click Place Order button.
Confirm popup with message "Please enter the number of pizzas in Quantity field".   (Currently this popup is not implemented)

4. Error checking - enter  letters and special characters in Quantity, confirm they are not accepted. (Currently this is not implemented)

5. Error checking - enter valid values EXCEPT Name and click Place Order button. Confirm popup stating Missing Name. 
Close popup, enter in Name value and click Place Order button. Confirm popup saying Thank you for valid order.
Repeat omitting Phone, then correct and confirm order placed.

#### Defects and Issues observed:
Reset should clear Toppings 1 and Toppings 2 dropdown fields which it currently does not do.

Payment Information fields are not connected radio buttons, both can be selected. Make them in same container so only one can be selected.

quantity number field should only allow non-negative numeric.  Currently alpha and special characters can be entered.
Check Quantity > 0 if "Place Order" clicked.
Consider having a max value allowed for Quantity.. 
Ask product owner if smaller maximum number should be allowed for Quantity. Perhaps if user tries to enter number > 10 have popup saying "For large orders please contact the store directly"

Implement further error checking when clicking "Place Order" .. verify a pizza type is selected.

Place "*" by Pizza1 and Quantity fields to show they are required entries.

UX - do better job formatting message, on confirmation popup. start "TOTAL" on new line,

UX - Currently when Place Order button is clicked and entries are valid, fields are cleared. Suggest not clearing form fields until after popoup confirmation is closed.

Suggest only enabling Toppings fields if pizza option with Toppings is selected.

if payment type not selected, popup message "Please select payment type" and retain all other field values.

format Cost to XX.XX, have "$" before field 

confirm valid format of email is entered if a value is entered. There is no validity checking on this field.
confirm valid format of phone number is entered. There is no validity checking on this field.

