import com.company.backinstock.dao.ProductDao;
import com.company.backinstock.model.Notification;
import com.company.backinstock.model.Product;
import com.company.backinstock.model.ProductCategory;
import com.company.backinstock.model.User;
import com.company.backinstock.service.StockServiceImpl;
import com.company.backinstock.service.SubscribedProductsService;
import java.util.HashMap;
import java.util.Random;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.*;

public class PriorityTest_Not_Premium_Older_Than_75 {
    @Test
    public void PriorityTest_Not_Premium_Older_Than_75() throws InterruptedException {
        ProductDao mockedProductDao = mock(ProductDao.class);
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <1001; i++ ) {
            if (i <= 330) {
                Product pill = new Product((long) i, "Pills"+i, ProductCategory.MEDICAL.get());
                products.add(pill);
            }
            if (i > 330 && i <= 700) {
                Product book = new Product((long) i, "Books"+i, ProductCategory.BOOK.get());
                products.add(book);
            }
            if (i > 700) {
                Product soft = new Product((long) i, "Digit.soft"+i, ProductCategory.DIGITAL.get());
                products.add(soft);
            }
        }
        when(mockedProductDao.getProducts()).thenReturn(products);

        SubscribedProductsService mockedSubscribedProductsService = mock(SubscribedProductsService.class);
        HashMap<User, List<Product>> subscribedProducts = new HashMap<>();
        subscribedProducts.put(new User("1", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("2", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("3", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("4", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("5", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("6", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("7", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("8", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("9", getRandomAge(), (long) getRandomId(), true), getRandomList(products));
        subscribedProducts.put(new User("10", 75, (long) getRandomId(), false), getRandomList(products));
        when(mockedSubscribedProductsService.getSubscribedProduct()).thenReturn(subscribedProducts);

        StockServiceImpl stockService = new StockServiceImpl(mockedProductDao, mockedSubscribedProductsService);
        stockService.addObservers(mockedSubscribedProductsService.getSubscribedProduct());
        List<Notification> result = stockService.process();
        assertThat(result, hasSize(30));
    }

    private int getRandomAge() {
        Random random = new Random();
        return random.nextInt(90 - 18) + 18;
    }

    private int getRandomProducts() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    private int getRandomId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    private List<Product> getRandomList(List<Product> products) {
        List<Product> productsFromUser = new ArrayList<>();
        int numberOfProducts = 0;
        while (numberOfProducts != 3) {
            productsFromUser.add(products.get(getRandomProducts()));
            numberOfProducts++;
        }
        return productsFromUser;
    }
}
