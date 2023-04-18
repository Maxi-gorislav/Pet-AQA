package ui.home;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.BaseUITests;
import ui.pages.home.CartPage;
import ui.pages.home.SearchPage;

@DisplayName("Home Page")
public class BaseTests extends BaseUITests {

    @Test
    @DisplayName("Verify exist 1 'mac' with proper price")
    public void findProperPriceInMac() {
        openHomePage().isLoaded()
                .clickFormCurrency()
                .clickCurrencyList("USD")
                .clickSearch()
                .searchText("mac");

        new SearchPage().isLoaded()
                .breadcrumbShouldContain("mac")
                .scrollToItem()
                .verifyProperPriceInItem("$26.46")
                .verifyMacBookCaption();
    }

    @Test
    @DisplayName("Verify MacBook Pro has proper price")
    public void findPriceByText() {
        openHomePage().isLoaded()
                .clickFormCurrency()
                .clickCurrencyList("USD")
                .clickSearch()
                .searchText("MacBook Pro");

        new SearchPage().isLoaded()
                .breadcrumbShouldContain("MacBook Pro")
                .scrollToItem()
                .verifyCorrectPrice("$2,096.43");
    }

    @Test
    @DisplayName("Add few item to cart")
    public void addQuantityInCart() {
        openHomePage().isLoaded()
                .clickSearch()
                .searchText("mac");

        new SearchPage().isLoaded()
                .breadcrumbShouldContain("mac")
                .scrollToItem()
                .selectItem("MacBook Air");

        new CartPage().isLoaded()
                .breadcrumbShouldContain("MacBook Air")
                .takeSomeItem(3)
                .clickUpdateButton()
                .cartExpend()
                .verifyCartHasProperItem("3");
    }
}
