package unify.basket.ui;

import unify.basket.controller.BasketPricerController;
import unify.basket.entities.Basket;
import unify.basket.entities.Product;
import unify.basket.entities.SpecialOffer;
import unify.basket.exceptions.BasketCreationException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static final String SEPARATOR = "---------------------------------------------------";

    public static void main(String[] args) {
        System.out.println(SEPARATOR);
        BasketPricerController basketPricerController = new BasketPricerController();
        basketPricerController.initFakeData();
        List<Product> products = basketPricerController.getAllProdcuts();
        System.out.println("----------------------AVAILABLE PRODUCTS-----------");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println(SEPARATOR);
        List<SpecialOffer> specialOffers = basketPricerController.listAvailablesSpecialOffers();
        for (SpecialOffer specialOffer : specialOffers) {
            System.out.println(specialOffer.getLabel());
        }
        System.out.println(SEPARATOR);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String[] userInput = scanner.nextLine().split(" ");

        if (userInput.length <= 1 || !"PriceBasket".equals(args[0])) {
            System.out.println("Unkonwn Command !!!!!");
            return;
        }

        try {
            Basket myBasket = basketPricerController.buy(Arrays.copyOfRange(userInput, 1, userInput.length));
            System.out.println(myBasket);
        } catch (BasketCreationException e) {
            System.out.println(e.getMessage());
        }


    }


}
