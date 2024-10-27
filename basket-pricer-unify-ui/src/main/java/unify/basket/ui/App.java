package unify.basket.ui;

import org.apache.commons.collections4.CollectionUtils;
import unify.basket.controller.BasketPricerController;
import unify.basket.entities.Basket;
import unify.basket.entities.Product;
import unify.basket.entities.SpecialOffer;
import unify.basket.exceptions.BasketCreationException;
import unify.basket.utils.CurrencyUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("---------------------------------------------------");
        BasketPricerController basketPricerController = new BasketPricerController();
        basketPricerController.initFakeData();
        List<Product> products = basketPricerController.getAllProdcuts();
        System.out.println("----------------------AVAILABLE PRODUCTS-----------");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("---------------------------------------------------");
        List<SpecialOffer> specialOffers = basketPricerController.listAvailablesSpecialOffers();
        for (SpecialOffer specialOffer : specialOffers) {
            System.out.println(specialOffer.getLabel());
        }
        System.out.println("---------------------------------------------------");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String[] userInput = scanner.nextLine().split(" ");

        if (userInput.length <= 1 || !"PriceBasket".equals(args[0])) {
            System.out.println("Unkonwn Command !!!!!");
            return;
        }

        try {
            Basket myBasket = basketPricerController.buy(Arrays.copyOfRange(userInput, 1, userInput.length));
            showBasket(myBasket);
        } catch (BasketCreationException e) {
            System.out.println(e.getMessage());
        }


    }

    private static void showBasket(Basket basket) {
        System.out.println("Subtotal: " + CurrencyUtils.format(basket.getSubTotal()));
        if (Objects.nonNull(basket.getSpecialOffer())) {
            System.out.println(basket.getSpecialOffer().getLabel());
        }
        if (CollectionUtils.isNotEmpty(basket.getProductList())) {
            basket.getProductList().stream().filter(p -> Objects.nonNull(p.getLastSpecialOffer())).forEach(product -> System.out.println(product.getSpecialOffersHistory().getFirst().getLabel()));
        }
        System.out.println("Total: " + CurrencyUtils.format(basket.getTotalPrice()));
    }


}
