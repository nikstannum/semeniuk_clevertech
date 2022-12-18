package by.clevertech.data.storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.clevertech.data.entity.Product;
import by.clevertech.data.repository.ProductRepository;

public class HandleProductRepoImpl implements ProductRepository {

    private final Map<Long, Product> repo;

    private long idSequence;

    public HandleProductRepoImpl() {
        this.repo = initRepoStub();
    }

    private long getIdSequence() {
        return ++idSequence;
    }

    private Map<Long, Product> initRepoStub() {
        Map<Long, Product> map = new HashMap<>();
        Product p1 = new Product();
        p1.setId(getIdSequence());
        p1.setName("milk");
        p1.setPrice(BigDecimal.valueOf(0.99));
        p1.setDiscount(false);
        repo.put(1L, p1);

        Product p2 = new Product();
        p2.setId(getIdSequence());
        p2.setName("yogurt");
        p2.setPrice(BigDecimal.valueOf(0.87));
        p2.setDiscount(false);
        repo.put(2L, p2);

        Product p3 = new Product();
        p3.setId(getIdSequence());
        p3.setName("bread");
        p3.setPrice(BigDecimal.valueOf(0.7));
        p3.setDiscount(false);
        repo.put(3L, p3);

        Product p4 = new Product();
        p4.setId(getIdSequence());
        p4.setName("cookie");
        p4.setPrice(BigDecimal.valueOf(1));
        p4.setDiscount(true);
        repo.put(4L, p4);

        Product p5 = new Product();
        p5.setId(getIdSequence());
        p5.setName("butter");
        p5.setPrice(BigDecimal.valueOf(1.05));
        p5.setDiscount(false);
        repo.put(5L, p5);

        Product p6 = new Product();
        p6.setId(getIdSequence());
        p6.setName("deodorant");
        p6.setPrice(BigDecimal.valueOf(2.18));
        p6.setDiscount(true);
        repo.put(6L, p6);

        Product p7 = new Product();
        p7.setId(getIdSequence());
        p7.setName("soap");
        p7.setPrice(BigDecimal.valueOf(0.89));
        p7.setDiscount(true);
        repo.put(7L, p7);

        Product p8 = new Product();
        p8.setId(getIdSequence());
        p8.setName("shower gel");
        p8.setPrice(BigDecimal.valueOf(2.94));
        p8.setDiscount(true);
        repo.put(8L, p8);

        Product p9 = new Product();
        p9.setId(getIdSequence());
        p9.setName("toothbrush");
        p9.setPrice(BigDecimal.valueOf(1.03));
        p9.setDiscount(true);
        repo.put(9L, p9);

        Product p10 = new Product();
        p10.setId(getIdSequence());
        p10.setName("toothpaste");
        p10.setPrice(BigDecimal.valueOf(1.79));
        p10.setDiscount(true);
        repo.put(10L, p10);

        Product p11 = new Product();
        p11.setId(getIdSequence());
        p11.setName("candies");
        p11.setPrice(BigDecimal.valueOf(3.18));
        p11.setDiscount(true);
        repo.put(11L, p11);

        return map;
    }

    @Override
    public Product create(Product entity) {
        Long id = getIdSequence();
        entity.setId(id);
        return repo.put(id, entity);
    }

    @Override
    public Product findById(Long id) {
        return repo.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public Product update(Product entity) {
        return repo.replace(entity.getId(), entity);
    }

    @Override
    public boolean delete(Long id) {
        return repo.remove(id) != null;
    }
}
