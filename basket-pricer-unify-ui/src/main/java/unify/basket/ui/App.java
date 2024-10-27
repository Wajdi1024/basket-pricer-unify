package unify.basket.ui;

import org.apache.commons.lang3.StringUtils;
import unify.basket.controller.BasketPricerController;
import unify.basket.entities.Basket;
import unify.basket.entities.Product;
import unify.basket.exceptions.BasketCreationException;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("---------------------------------------------------");
        String userInput = StringUtils.join(args, " ");
        System.out.println("Command: " + userInput);
        if (args.length <= 1 || !"PriceBasket".equals(args[0])) {
            System.out.println("Unkonwn Command !!!!!");
            return;
        }
        BasketPricerController basketPricerController = new BasketPricerController();
        basketPricerController.initFakeData();
        List<Product> products = basketPricerController.getAllProdcuts();
        System.out.println("----------------------AVAILABLE PRODUCTS-----------");
        for (Product product : products) {
            showProduct(product);
        }
        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");

        try {
            Basket myBasket = basketPricerController.buy(Arrays.copyOfRange(args, 1, args.length));
            showBasket(myBasket);
        } catch (BasketCreationException e) {
            System.out.println(e.getMessage());
        }


    }

    private static void showBasket(Basket basket) {
        System.out.println("Subtotal: " + basket.getSubTotal());
        System.out.println("Total: " + basket.getTotalPrice());
    }

    private static void showProduct(Product product) {
        System.out.println(String.format("product: %s price: %s", product.getName(), product.getLastPrice().getValue()));
    }

}
