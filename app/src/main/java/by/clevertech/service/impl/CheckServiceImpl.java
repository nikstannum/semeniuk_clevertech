package by.clevertech.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.clevertech.data.entity.Check;
import by.clevertech.data.entity.CheckItem;
import by.clevertech.data.entity.DiscountCard;
import by.clevertech.data.entity.Product;
import by.clevertech.data.repository.CardRepository;
import by.clevertech.data.repository.ProductRepository;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
	private final ProductRepository productRepository;
	private final CardRepository cardRepository;

	@Override
	public Check get(CheckInDto checkInputDto) {
		Check check = new Check();
		List<CheckItem> list = getCheckItems(checkInputDto);
		check.setProducts(list);
		/*
		 * FIXME setTotalCost with and without discount to check setHeader
		 */
		Long cardId = checkInputDto.getCardId();
		/*
		 * TODO Processing EntNotFoundExc: if the card is not found, then the
		 * application should not crash
		 */
		BigDecimal costWithoutDiscounts = totalCostWithoutDiscounts(list);
		BigDecimal costWithoutCard = totalCostWithoutCard(list);
		if (cardId != null) {
			BigDecimal costWithCard = totalCostWithCard(costWithoutCard, cardId);
			check.setTotalCost(costWithCard);
			return check;
		}
		check.setTotalCost(costWithoutCard);
		check.setTimestamp(LocalDateTime.now());

		String cashReceipt = "CASH RECEIPT";
		String storeNumber = "SUPERMARKET 123";
		String address = "12, MILKYWAY Galaxy/ Earth";
		String tel = "Tel: 123-456-7890";
		String cashier = "CASHIER: Marpha Vasilevna";
		
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = dateTime.toLocalDate();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = date.format(dateFormat);
		LocalTime time = dateTime.toLocalTime();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedTime = time.format(timeFormat);

		return check;
	}

	private List<CheckItem> getCheckItems(CheckInDto checkInputDto) {
		List<CheckItem> items = new ArrayList<>();
		Map<Long, Integer> products = checkInputDto.getProducts();
		for (Long productId : products.keySet()) {
			CheckItem item = new CheckItem();
			Product product = productRepository.findById(productId);
			Integer quantity = products.get(productId);
			BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
			item.setQuantity(quantity);
			item.setProduct(product);
			item.setTotal(total);
			items.add(item);
		}
		return items;
	}

	private BigDecimal totalCostWithoutDiscounts(List<CheckItem> items) {
		BigDecimal totalCostWithoutDiscount = BigDecimal.ZERO;
		for (CheckItem item : items) {
			totalCostWithoutDiscount = totalCostWithoutDiscount.add(item.getTotal());
		}
		return totalCostWithoutDiscount.setScale(2, RoundingMode.HALF_UP);
	}

	private BigDecimal costDicsountItem(CheckItem item) {
		BigDecimal discountForDiscountProduct = BigDecimal.valueOf(10); // FIXME Magic number (discount size = 10%)
		BigDecimal discountFactor = BigDecimal.valueOf(100).subtract(discountForDiscountProduct);
		BigDecimal cost = item.getTotal().multiply(discountFactor).divide(BigDecimal.valueOf(100));
		return cost.setScale(2, RoundingMode.HALF_UP);
	}

	private BigDecimal totalCostWithoutCard(List<CheckItem> items) {
		BigDecimal totalCost = BigDecimal.ZERO;
		for (CheckItem item : items) {
			Integer quantity = item.getQuantity();
			Product product = item.getProduct();
			if (quantity > 5 && product.isDiscount()) {
				totalCost = totalCost.add(costDicsountItem(item));
			} else {
				totalCost = totalCost.add(item.getTotal());
			}
		}
		return totalCost.setScale(2, RoundingMode.HALF_UP);
	}

	private BigDecimal totalCostWithCard(BigDecimal cost, Long cardId) {
		BigDecimal totalCost = cost;
		DiscountCard card = cardRepository.findById(cardId);
		if (card != null) { // FIXME how will to processing better?
			BigDecimal discountSize = card.getDiscountSize();
			BigDecimal discountFactor = BigDecimal.valueOf(100).subtract(discountSize);
			totalCost = totalCost.multiply(discountFactor).divide(BigDecimal.valueOf(100));
		}
		return totalCost.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public Check findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
