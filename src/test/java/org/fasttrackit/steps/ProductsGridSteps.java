package org.fasttrackit.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.ProductGrid;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class ProductsGridSteps extends TestBase {

    private ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);

    @When("^I sort products by \"([^\"]*)\" in (.+) order$")
    public void iSortProductsByInAscendingOrder(String sortCriteria, String sortDirection) {

        productGrid.getSortBySelectList().selectByVisibleText(sortCriteria);

        String sortDirectionButtonClass = productGrid.getSortDirectionButton().getAttribute("class");
        if ((sortDirectionButtonClass.contains("--asc") && sortDirection.equals("descending")) ||
                ((sortDirectionButtonClass.contains("--desc") && sortDirection.equals("asscending")))
        ) {
            productGrid.getSortDirectionButton().click();
        }

    }

    @Then("^all products are sorted by \"([^\"]*)\" in (.+) order$")
    public void allProductsAreSortedByInAscendingOrder(String sortCriteria, String sortDirection) {


        Comparator comparator;

        if (sortDirection.equalsIgnoreCase("ascending")) {
            comparator = Comparator.naturalOrder();

        } else {
            comparator = Comparator.reverseOrder();
        }

        if (sortCriteria.equalsIgnoreCase("Price")) {
            assertCorrectSortByPrice(comparator);

        } else if (sortCriteria.equalsIgnoreCase("Name")) {
            assertCorrectSortByNames(comparator);

        } else {
            throw new PendingException("assertion for sort by " + sortCriteria + " not implemented");
        }
    }

    private void assertCorrectSortByNames(Comparator comparator) {
        List<String> names = productGrid.getProductsName();
        //copie a unei liste
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(comparator);
        assertThat("Products are not sorted correctly", names, equalTo(sortedNames));
    }

    private void assertCorrectSortByPrice(Comparator comparator) {
        List<Double> prices = productGrid.getActualProductPricesAsDoubles();
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(comparator);
        assertThat("Products are not sorted corectly", prices, equalTo(sortedPrices));
    }


    @And("^I store the name of the (\\d+)(?:.+?) product with Add to Cart button$")
    public void iStoreTheNameOfTheStProductWithAddToCartButton(int productNumber) {
        List<WebElement> nameContainers = productGrid.getAddToCartProductNameContainer();

        assertThat("Not enough products displayed ", nameContainers.size(), greaterThanOrEqualTo(productNumber));

        String productName = nameContainers.get(productNumber - 1).getText();
        getStepVariables().put("addToCartProductName", productName);

    }
}
