# Insider Test Automation Framework

This project is a **Java – Selenium – TestNG** based automation framework developed to run test scenarios on the **Insider website**.
The framework is built on the **Page Object Model (POM)** architecture and provides detailed reporting with **Allure Report**.
Additionally, tests can be run flexibly at the class or method level using **TestNG XML** files.

---

## 🚀 Technologies Used

- **Java 21**
- **Selenium WebDriver 4.35**
- **TestNG**
- **Page Object Model (POM)**
- **Allure Report**
- **Maven & Surefire Plugin**
- **Custom Utilities (Driver, Wait, Commands, Asserts, GeneralReader)**

---

## 📂  Project Structure

src
├── main
│   └── java
├── test
│   ├── java
│   │   ├── hooks          # Listener (Allure + Screenshot on failure)
│   │   ├── pages          # Page Object Model classes (HomePage, CareersPage, QAJobsPage)
│   │   ├── tests          # Test classes (InsiderTests)
│   │   └── utilities      # Utility classes (Driver, Wait, Commands, Asserts, GeneralReader)
│   └── resources
│       ├── testng.xml         # Runs all tests
│       ├── testng_class.xml   # Runs specific class
│       ├── testng_method.xml  # Runs specific method
│       └── Configuration.properties
└── pom.xml



---

## ✅ Test Scenarios

1. **Home page loading**  
   - Verifies that the Insider home page loads successfully.  

2. **Company → Careers menu navigation**  
   - Verifies navigation to the Careers page.  
   - Checks that the **Teams**, **Locations**, and **Life at Insider** blocks are displayed.  

3. **QA Jobs filtering**  
   - Clicks the "**See all QA jobs**" button on the QA page.  
   - Selects "**Istanbul, Turkey**" in the location filter.  
   - Verifies that job positions are listed.  

4. **Job details validation**  
   - Checks that position names contain "**Quality Assurance**",  
   - Verifies that the department is "**Quality Assurance**",  
   - Confirms that the location is "**Istanbul, Turkiye**".  

5. **View Role redirection**  
   - Verifies that clicking the "**View Role**" button redirects to the Lever application page.  

---

## ⚙️ Setup & Execution

1. Clone the project:
   ```bash
   git clone https://github.com/clgnmmr/kadir_tepecik_case.git
   cd kadir_tepecik_case

2. Install Maven dependencies:
   ```bash
   mvn clean install

3. Run all tests:
    ```bash
    mvn clean test

4. Run with a specific XML file:
    ```bash
   mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng_class.xml

---
## 📂 Test results are stored in the allure-results/ directory after execution.

  To open the report:

   mvn allure:serve

    
---
## Notes
The **Configuration.properties** file allows customization of **base_url**, **qa_page**, **location**, and **department** values.
The **Hook class** captures screenshots on test failure and attaches them to the **Allure report**.
The **testng.xml** includes a listener to ensure reporting works for all tests.
