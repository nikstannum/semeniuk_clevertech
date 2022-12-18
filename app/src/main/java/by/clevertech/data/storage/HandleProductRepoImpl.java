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
        Map<Long, Product> repo = new HashMap<>();
        Product p1 = new Product();
        Long id1 = getIdSequence();
        p1.setId(id1);
        p1.setName("milk");
        p1.setPrice(BigDecimal.valueOf(0.99));
        p1.setDiscount(false);
        repo.put(id1, p1);

        Product p2 = new Product();
        Long id2 = getIdSequence();
        p2.setId(id2);
        p2.setName("yogurt");
        p2.setPrice(BigDecimal.valueOf(0.87));
        p2.setDiscount(false);
        repo.put(id2, p2);

        Product p3 = new Product();
        Long id3 = getIdSequence();
        p3.setId(id3);
        p3.setName("bread");
        p3.setPrice(BigDecimal.valueOf(0.7));
        p3.setDiscount(false);
        repo.put(id3, p3);

        Product p4 = new Product();
        Long id4 = getIdSequence();
        p4.setId(id4);
        p4.setName("cookie");
        p4.setPrice(BigDecimal.valueOf(1));
        p4.setDiscount(true);
        repo.put(id4, p4);

        Product p5 = new Product();
        Long id5 = getIdSequence();
        p5.setId(id5);
        p5.setName("butter");
        p5.setPrice(BigDecimal.valueOf(1.05));
        p5.setDiscount(false);
        repo.put(id5, p5);

        Product p6 = new Product();
        Long id6 = getIdSequence();
        p6.setId(id6);
        p6.setName("deodorant");
        p6.setPrice(BigDecimal.valueOf(2.18));
        p6.setDiscount(true);
        repo.put(id6, p6);

        Product p7 = new Product();
        Long id7 = getIdSequence();
        p7.setId(id7);
        p7.setName("soap");
        p7.setPrice(BigDecimal.valueOf(0.89));
        p7.setDiscount(true);
        repo.put(id7, p7);

        Product p8 = new Product();
        Long id8 = getIdSequence();
        p8.setId(id8);
        p8.setName("shower gel");
        p8.setPrice(BigDecimal.valueOf(2.94));
        p8.setDiscount(true);
        repo.put(id8, p8);

        Product p9 = new Product();
        Long id9 = getIdSequence();
        p9.setId(id9);
        p9.setName("toothbrush");
        p9.setPrice(BigDecimal.valueOf(1.03));
        p9.setDiscount(true);
        repo.put(id9, p9);

        Product p10 = new Product();
        Long id10 = getIdSequence();
        p10.setId(id10);
        p10.setName("toothpaste");
        p10.setPrice(BigDecimal.valueOf(1.79));
        p10.setDiscount(true);
        repo.put(id10, p10);

        Product p11 = new Product();
        Long id11 = getIdSequence();
        p11.setId(id11);
        p11.setName("candies");
        p11.setPrice(BigDecimal.valueOf(3.18));
        p11.setDiscount(true);
        repo.put(id11, p11);

        return repo;
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
