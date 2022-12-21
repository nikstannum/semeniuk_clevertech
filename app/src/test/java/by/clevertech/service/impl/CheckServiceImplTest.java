package by.clevertech.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import by.clevertech.data.entity.DiscountCard;
import by.clevertech.data.entity.Product;
import by.clevertech.data.repository.CardRepository;
import by.clevertech.data.repository.ProductRepository;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInDto;
import by.clevertech.service.dto.CheckOutDto;
import by.clevertech.service.exception.EntityNotFoundException;

public class CheckServiceImplTest {
    private static CheckService checkService;
    private static CheckInDto discountProductsMoreFiveWithCardValid;
    private static CheckInDto discountProductsLessFiveWithCardValid;

    @BeforeAll
    static void beforeAll() {
        getCheckService();
        discountProductsMoreFiveWithCardValid = discountProductsMoreFiveWithCardValid();
        discountProductsLessFiveWithCardValid = discountProductsLessFiveWithCardValid();
    }

    @Test
    void fullCostMoreFiveDiscountProduct() {
        CheckOutDto actual = checkService.get(discountProductsMoreFiveWithCardValid);
        assertEquals(BigDecimal.valueOf(13.21), actual.getFullCost());
    }

    @Test
    void totalCostWithoutCardMoreFiveDiscountProduct() {
        CheckInDto in = discountProductsMoreFiveWithoutCardValid();
        CheckOutDto actual = checkService.get(in);
        assertEquals(BigDecimal.valueOf(12.61), actual.getTotalCost());
    }

    @Test
    void totalCostWithCardMoreFiveDiscountProduct() {
        CheckOutDto actual = checkService.get(discountProductsMoreFiveWithCardValid);
        assertEquals(BigDecimal.valueOf(12.42), actual.getTotalCost());
    }

    @Test
    void discountSizeMoreFiveDiscountProduct() {
        CheckOutDto actual = checkService.get(discountProductsMoreFiveWithCardValid);
        BigDecimal discount = actual.getFullCost().subtract(actual.getTotalCost());
        assertEquals(BigDecimal.valueOf(0.79), discount);
    }

    @Test
    void fullCostLessFiveDiscountProduct() {
        CheckOutDto actual = checkService.get(discountProductsLessFiveWithCardValid);
        assertEquals(BigDecimal.valueOf(9.21), actual.getFullCost());
    }

    @Test
    void totalCostWithoutCardLessFiveDiscountProduct() {
        CheckInDto in = discountProductsLessFiveWithoutCardValid();
        CheckOutDto actual = checkService.get(in);
        assertEquals(BigDecimal.valueOf(9.21), actual.getTotalCost());
    }

    @Test
    void totalCostWithCardLessFiveDiscountProduct() {
        CheckOutDto actual = checkService.get(discountProductsLessFiveWithCardValid);
        assertEquals(BigDecimal.valueOf(9.07), actual.getTotalCost());
    }

    @Test
    void discountSizeLessFiveDiscountProduct() {
        CheckOutDto actual = checkService.get(discountProductsLessFiveWithCardValid);
        BigDecimal discount = actual.getFullCost().subtract(actual.getTotalCost());
        assertEquals(BigDecimal.valueOf(0.14), discount);
    }

    @Test
    void invalidProduct() {
        CheckInDto in = invalidProductWithValidCard();
        assertThrows(EntityNotFoundException.class, () -> checkService.get(in));
    }

    @Test
    void invalidCard() {
        CheckInDto in = invalidCardWithValidProduct();
        assertThrows(EntityNotFoundException.class, () -> checkService.get(in));
    }

    private static void getCheckService() {
        ProductRepository productRepository = mockProductRepository();
        CardRepository cardRepository = mockCardRepository();
        checkService = new CheckServiceImpl(productRepository, cardRepository);
    }

    private static CheckInDto discountProductsMoreFiveWithCardValid() {
        CheckInDto validInDto = new CheckInDto();
        validInDto.setCardId(1L);
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 2);
        map.put(2L, 1);
        map.put(4L, 6);
        map.put(6L, 2);
        validInDto.setProducts(map);
        return validInDto;
    }

    private static CheckInDto discountProductsMoreFiveWithoutCardValid() {
        CheckInDto validInDto = new CheckInDto();
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 2);
        map.put(2L, 1);
        map.put(4L, 6);
        map.put(6L, 2);
        validInDto.setProducts(map);
        return validInDto;
    }

    private static CheckInDto discountProductsLessFiveWithoutCardValid() {
        CheckInDto validInDto = new CheckInDto();
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 2);
        map.put(2L, 1);
        map.put(4L, 2);
        map.put(6L, 2);
        validInDto.setProducts(map);
        return validInDto;
    }

    private static CheckInDto discountProductsLessFiveWithCardValid() {
        CheckInDto validInDto = new CheckInDto();
        validInDto.setCardId(1L);
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 2);
        map.put(2L, 1);
        map.put(4L, 2);
        map.put(6L, 2);
        validInDto.setProducts(map);
        return validInDto;
    }

    private static CheckInDto invalidProductWithValidCard() {
        CheckInDto validInDto = new CheckInDto();
        validInDto.setCardId(1L);
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 2);
        map.put(2L, 1);
        map.put(4L, 2);
        map.put(999L, 2);
        validInDto.setProducts(map);
        return validInDto;
    }

    private static CheckInDto invalidCardWithValidProduct() {
        CheckInDto validInDto = new CheckInDto();
        validInDto.setCardId(999L);
        Map<Long, Integer> map = new HashMap<>();
        map.put(1L, 2);
        map.put(2L, 1);
        map.put(4L, 2);
        map.put(6L, 2);
        validInDto.setProducts(map);
        return validInDto;
    }

    private static CardRepository mockCardRepository() {
        CardRepository cardRepository = Mockito.mock(CardRepository.class);
        DiscountCard card = new DiscountCard();
        card.setCardId(1L);
        card.setDiscountSize(BigDecimal.valueOf(1.5));
        Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        Mockito.when(cardRepository.findById(999L)).thenThrow(new EntityNotFoundException());
        return cardRepository;
    }

    private static ProductRepository mockProductRepository() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Product milk = new Product();
        milk.setId(1L);
        milk.setName("milk");
        milk.setPrice(BigDecimal.valueOf(0.99));
        milk.setDiscount(false);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(milk));

        Product yogurt = new Product();
        yogurt.setId(2L);
        yogurt.setName("yogurt");
        yogurt.setPrice(BigDecimal.valueOf(0.87));
        yogurt.setDiscount(false);
        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(yogurt));

        Product cookie = new Product();
        cookie.setId(4L);
        cookie.setName("cookie");
        cookie.setPrice(BigDecimal.valueOf(1));
        cookie.setDiscount(true);
        Mockito.when(productRepository.findById(4L)).thenReturn(Optional.of(cookie));

        Product deodorant = new Product();
        deodorant.setId(6L);
        deodorant.setName("deodorant");
        deodorant.setPrice(BigDecimal.valueOf(2.18));
        deodorant.setDiscount(true);
        Mockito.when(productRepository.findById(6L)).thenReturn(Optional.of(deodorant));

        Mockito.when(productRepository.findById(999L)).thenThrow(new EntityNotFoundException());
        return productRepository;
    }
}
