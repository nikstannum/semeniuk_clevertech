package by.clevertech.data.storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.clevertech.data.entity.DiscountCard;
import by.clevertech.data.repository.CardRepository;

public class HandleCardRepoImpl implements CardRepository {

    private final Map<Long, DiscountCard> repo;

    private long idSequence;

    public HandleCardRepoImpl(Map<Long, DiscountCard> repo) {
        this.repo = initRepoStub();
    }

    private long getIdSequence() {
        return ++idSequence;
    }

    private Map<Long, DiscountCard> initRepoStub() {
        Map<Long, DiscountCard> repo = new HashMap<>();

        DiscountCard c1 = new DiscountCard();
        Long id1 = getIdSequence();
        c1.setCardId(id1);
        c1.setDiscountSize(BigDecimal.valueOf(1.5));
        repo.put(id1, c1);

        DiscountCard c2 = new DiscountCard();
        Long id2 = getIdSequence();
        c2.setCardId(id2);
        c2.setDiscountSize(BigDecimal.valueOf(5));
        repo.put(id2, c2);

        DiscountCard c3 = new DiscountCard();
        Long id3 = getIdSequence();
        c3.setCardId(id3);
        c3.setDiscountSize(BigDecimal.valueOf(3));
        repo.put(id3, c3);

        return repo;
    }

    @Override
    public DiscountCard create(DiscountCard entity) {
        Long id = getIdSequence();
        entity.setCardId(id);
        return repo.put(id, entity);
    }

    @Override
    public DiscountCard findById(Long id) {
        return repo.get(id);
    }

    @Override
    public List<DiscountCard> findAll() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public DiscountCard update(DiscountCard entity) {
        return repo.replace(entity.getCardId(), entity);
    }

    @Override
    public boolean delete(Long id) {
        return repo.remove(id) != null;
    }

}
