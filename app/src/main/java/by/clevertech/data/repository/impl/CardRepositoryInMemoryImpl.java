package by.clevertech.data.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.clevertech.data.entity.DiscountCard;
import by.clevertech.data.repository.CardRepository;
import by.clevertech.service.exception.EntityNotFoundException;

/**
 * Implements {@link CardRepository}
 * <p>
 * Class for data access {@link DiscountCard}.
 * <p>
 * The class is like an in-memory database. Filling with data is carried out at
 * runtime.
 * 
 * @author Nikita Semeniuk
 *
 */
public class CardRepositoryInMemoryImpl implements CardRepository {

    private static final String EXC_MSG_NOT_FOUND = "wasn't found card with id = ";

    private final Map<Long, DiscountCard> repo;

    private long idSequence;

    public CardRepositoryInMemoryImpl() {
        this.repo = initRepoStub();
    }

    @Override
    public Optional<DiscountCard> findById(Long id) {
        try {
            return Optional.of(repo.get(id));
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(EXC_MSG_NOT_FOUND + id, e);
        }
    }

    @Override
    public Optional<DiscountCard> create(DiscountCard entity) {
        Long id = getIdSequence();
        entity.setCardId(id);
        return Optional.of(repo.put(id, entity));
    }

    @Override
    public List<DiscountCard> findAll() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public Optional<DiscountCard> update(DiscountCard entity) {
        DiscountCard updated = repo.replace(entity.getCardId(), entity);
        return Optional.of(updated);
    }

    @Override
    public boolean delete(Long id) {
        return repo.remove(id) != null;
    }

    /**
     * assigns an id to an object
     * 
     * @return object id
     */
    private long getIdSequence() {
        return ++idSequence;
    }

    /**
     * fills the map with data
     * 
     * @return filled map
     */
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
}
