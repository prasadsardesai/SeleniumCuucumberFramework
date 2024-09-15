package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * BasePage class that contains common methods for interacting with web
 * elements.
 */
public class BasePage {

	protected WebDriver driver; // WebDriver instance
	private WebDriverWait wait; // WebDriverWait instance

	// Constructor to initialize WebDriver and WebDriverWait
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Default wait time
	}
	
	// ================== Launch Url Method ==================

	/**
	 * Method to launch the application.
	 * 
	 * @param The url of the application as a String.
	*/
	
	public void get(String url) {
		driver.get(url);
	}

	// ================== Page Title Methods ==================

	/**
	 * Method to get the title of the current page.
	 * 
	 * @return The title of the current page as a String.
	 */
	public String getTitle() {
		return driver.getTitle(); // Return the title of the current page
	}

	/**
	 * Fetches the title of the current page, ensuring the page has loaded.
	 *
	 * @param locator The By locator to wait for before fetching the title.
	 * @return The title of the current page or a fallback message if the title is
	 *         not available.
	 */
	public String fetchTitle(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Wait for the element to be visible
		} catch (Exception e) {
			System.out.println("Element not found, proceeding to get title anyway.");
		}
		return driver.getTitle(); // Return the title of the current page
	}

	// ================== Click Methods ==================

	/**
	 * Method to click on a web element.
	 * 
	 * @param locator The By locator for the element to click.
	 */
	protected void click(By locator) {
		WebElement element = waitUntilVisible(locator); // Wait until the element is visible
		element.click(); // Click the element
	}

	/**
	 * Method to click on a web element after checking if it is displayed.
	 * 
	 * @param locator The By locator for the element to click.
	 */
	protected void clickIfDisplayed(By locator) {
		if (isDisplayed(locator)) { // Check if the element is displayed
			driver.findElement(locator).click(); // Click the element
		} else {
			System.out.println("Element not displayed: " + locator); // Log if not displayed
		}
	}

	/**
	 * Method to click on an element using JavaScript.
	 * 
	 * @param locator The By locator for the element to click.
	 */
	protected void clickUsingJavaScript(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Wait until visible
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	// ================== Text Handling Methods ==================

	/**
	 * Method to enter text into a web element.
	 * 
	 * @param locator The By locator for the element.
	 * @param text    The text to enter.
	 */
	protected void enterText(By locator, String text) {
		WebElement element = waitUntilVisible(locator); // Wait until the element is visible
		element.clear(); // Clear any existing text
		element.sendKeys(text); // Enter the new text
	}

	/**
	 * Method to get the text of a web element.
	 * 
	 * @param locator The By locator for the element.
	 * @return The text of the element.
	 */
	protected String getText(By locator) {
		WebElement element = waitUntilVisible(locator); // Wait until the element is visible
		return element.getText(); // Return the text of the element
	}

	// ================== Visibility & Existence Methods ==================

	/**
	 * Method to check if a web element is displayed.
	 * 
	 * @param locator The By locator for the element.
	 * @return true if the element is displayed, false otherwise.
	 */
	protected boolean isDisplayed(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator)); // Wait until visible
			return element.isDisplayed(); // Return true if the element is displayed
		} catch (Exception e) {
			return false; // Return false if any issue occurs (e.g., element not found)
		}
	}

	/**
	 * Method to wait until a web element is visible.
	 * 
	 * @param locator The By locator for the element.
	 * @return The WebElement once it is visible.
	 */
	private WebElement waitUntilVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Wait for visibility
	}

	// ================== Radio Button Methods ==================

	/**
	 * Selects a radio button using JavaScriptExecutor if it is not already
	 * selected.
	 *
	 * @param locator The By locator for the radio button to be selected.
	 */
	public void selectRadioButton(By locator) {
		if (locator != null) {
			WebElement radioButton = driver.findElement(locator);
			if (!radioButton.isSelected()) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].checked = true;", radioButton);
				// Optionally, trigger the change event if needed
				js.executeScript("arguments[0].dispatchEvent(new Event('change'));", radioButton);
			}
		} else {
			throw new IllegalArgumentException("Locator cannot be null");
		}
	}

	// ================== Dropdown Methods ==================

	/**
	 * Selects an option from a dropdown by visible text.
	 * 
	 * @param locator     The By locator for the dropdown.
	 * @param visibleText The visible text of the option to select.
	 */
	public void selectDropdownByVisibleText(By locator, String visibleText) {
		WebElement dropdown = waitUntilVisible(locator); // Wait until the dropdown is visible
		Select select = new Select(dropdown); // Create a Select object
		select.selectByVisibleText(visibleText); // Select the option by visible text
	}

	/**
	 * Selects an option from a dropdown by value.
	 * 
	 * @param locator The By locator for the dropdown.
	 * @param value   The value of the option to select.
	 */
	public void selectDropdownByValue(By locator, String value) {
		WebElement dropdown = waitUntilVisible(locator); // Wait until the dropdown is visible
		Select select = new Select(dropdown); // Create a Select object
		select.selectByValue(value); // Select the option by value
	}

	/**
	 * Selects an option from a dropdown by index.
	 * 
	 * @param locator The By locator for the dropdown.
	 * @param index   The index of the option to select (0-based).
	 */
	public void selectDropdownByIndex(By locator, int index) {
		WebElement dropdown = waitUntilVisible(locator); // Wait until the dropdown is visible
		Select select = new Select(dropdown); // Create a Select object
		select.selectByIndex(index); // Select the option by index
	}

	// ================== Mouse Action Methods ==================

	/**
	 * Method to hover over a web element.
	 * 
	 * @param locator The By locator for the element to hover over.
	 */
	public void mouseHover(By locator) {
		WebElement element = waitUntilVisible(locator); // Wait until the element is visible
		Actions actions = new Actions(driver); // Create an Actions object
		actions.moveToElement(element).perform(); // Perform the hover action
	}

	/**
	 * Method to double-click on a web element.
	 * 
	 * @param locator The By locator for the element to double-click.
	 */
	public void doubleClick(By locator) {
		WebElement element = waitUntilVisible(locator); // Wait until the element is visible
		Actions actions = new Actions(driver); // Create an Actions object
		actions.doubleClick(element).perform(); // Perform the double-click action
	}

	/**
	 * Method to drag and drop an element from source to target.
	 * 
	 * @param sourceLocator The By locator for the element to drag.
	 * @param targetLocator The By locator for the target element to drop.
	 */
	public void dragAndDrop(By sourceLocator, By targetLocator) {
		WebElement sourceElement = waitUntilVisible(sourceLocator); // Wait until the source element is visible
		WebElement targetElement = waitUntilVisible(targetLocator); // Wait until the target element is visible
		Actions actions = new Actions(driver); // Create an Actions object
		actions.dragAndDrop(sourceElement, targetElement).perform(); // Perform the drag and drop action
	}

	// ================== Table Handling Methods ==================

	/**
	 * Get the number of rows in a static table.
	 * 
	 * @param tableLocator The By locator for the table.
	 * @return The number of rows in the table.
	 */
	public int getRowCount(By tableLocator) {
		WebElement table = waitUntilVisible(tableLocator); // Wait until the table is visible
		List<WebElement> rows = table.findElements(By.tagName("tr")); // Find all rows
		return rows.size(); // Return the number of rows
	}

	/**
	 * Get the number of columns in a static table.
	 * 
	 * @param tableLocator The By locator for the table.
	 * @return The number of columns in the first row of the table.
	 */
	public int getColumnCount(By tableLocator) {
		WebElement table = waitUntilVisible(tableLocator); // Wait until the table is visible
		List<WebElement> columns = table.findElements(By.xpath("//tr[1]/td")); // Find all columns in the first row
		return columns.size(); // Return the number of columns
	}

	/**
	 * Get the text of a specific cell in a static table.
	 * 
	 * @param tableLocator The By locator for the table.
	 * @param rowIndex     The index of the row (0-based).
	 * @param columnIndex  The index of the column (0-based).
	 * @return The text of the specified cell.
	 */
	public String getCellText(By tableLocator, int rowIndex, int columnIndex) {
		WebElement table = waitUntilVisible(tableLocator); // Wait until the table is visible
		List<WebElement> rows = table.findElements(By.tagName("tr")); // Find all rows
		List<WebElement> columns = rows.get(rowIndex).findElements(By.tagName("td")); // Find all columns in the
																						// specified row
		return columns.get(columnIndex).getText(); // Return the text of the specified cell
	}

	/**
	 * Navigate to the next page in a paginated table.
	 * 
	 * @param nextPageLocator The By locator for the 'Next' button.
	 */
	public void goToNextPage(By nextPageLocator) {
		click(nextPageLocator); // Click the 'Next' button to go to the next page
	}

	/**
	 * Check if the last page is reached in a paginated table.
	 * 
	 * @param lastPageLocator The By locator for the last page indicator.
	 * @return true if the last page is reached, false otherwise.
	 */
	public boolean isLastPage(By lastPageLocator) {
		return isDisplayed(lastPageLocator); // Check if the last page indicator is displayed
	}

}