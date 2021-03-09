import com.company.backinstock.dao.ProductDao;
import com.company.backinstock.model.Notification;
import com.company.backinstock.model.Product;
import com.company.backinstock.model.ProductCategory;
import com.company.backinstock.model.User;
import com.company.backinstock.service.StockServiceImpl;
import com.company.backinstock.service.SubscribedProductsService;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.*;

public class BasicTest_ok {
    @Test
    public void BasicTest_ok() throws InterruptedException {
        ProductDao mockedProductDao = mock(ProductDao.class);
        List<Product> products = new ArrayList<>();
        Product pill = new Product(1L, "Pills", ProductCategory.MEDICAL.get());
        products.add(pill);
        when(mockedProductDao.getProducts()).thenReturn(products);

        SubscribedProductsService mockedSubscribedProductsService = mock(SubscribedProductsService.class);
        HashMap<User, List<Product>> subscribedProducts = new HashMap<>();
        subscribedProducts.put(new User("1", 75,1L,false), List.of(pill));
        when(mockedSubscribedProductsService.getSubscribedProduct()).thenReturn(subscribedProducts);

        StockServiceImpl stockService = new StockServiceImpl(mockedProductDao, mockedSubscribedProductsService);
        stockService.addObservers(mockedSubscribedProductsService.getSubscribedProduct());
        List<Notification> result = stockService.process();
        assertThat(result, hasSize(1));
    }
}
