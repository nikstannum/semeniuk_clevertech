package by.clevertech.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.clevertech.dao.entity.Check;
import by.clevertech.dao.entity.CheckItem;
import by.clevertech.dao.entity.DiscountCard;
import by.clevertech.dao.entity.Product;
import by.clevertech.dao.repository.CardRepository;
import by.clevertech.dao.repository.ProductRepository;
import by.clevertech.service.CheckService;
import by.clevertech.service.dto.CheckInputDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
	private final ProductRepository productRepository;
	private final CardRepository cardRepository;

	@Override
	public Check get(CheckInputDto checkInputDto) {

		// TODO Auto-generated method stub
		return null;
	}

	private List<CheckItem> getCheckItems(CheckInputDto checkInputDto) {
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
		BigDecimal costWithotDiscout = totalCostWithoutDiscount(items);
		BigDecimal costWithDiscountProducts = costWithDiscountProducts(items);
		BigDecimal totalCost = totalCostWithDiscountCard(costWithDiscountProducts, checkInputDto.getCardId());

		// FIXME setTotalCost to items
		return items;
	}

	private BigDecimal totalCostWithoutDiscount(List<CheckItem> items) {
		BigDecimal totalCostWithoutDiscount = BigDecimal.ZERO;
		for (CheckItem item : items) {
			totalCostWithoutDiscount.add(item.getTotal().multiply(BigDecimal.valueOf(item.getQuantity())));
		}
		return totalCostWithoutDiscount;
	}

	private BigDecimal costDicsountItem(CheckItem item) {
		BigDecimal cost = item.getTotal().multiply(BigDecimal.valueOf(0.1)); // FIXME Magic number
		return cost;
	}

	private BigDecimal costWithDiscountProducts(List<CheckItem> items) {
		BigDecimal totalCost = BigDecimal.ZERO;
		for (CheckItem item : items) {
			Integer quantity = item.getQuantity();
			Product product = item.getProduct();
			if (quantity > 5 && product.isDiscount()) {
				totalCost.add(costDicsountItem(item));
			} else {
				totalCost.add(item.getTotal());
			}
		}
		return totalCost;
	}

	private BigDecimal totalCostWithDiscountCard(BigDecimal cost, Long cardId) {
		BigDecimal totalCost = cost;
		DiscountCard card = cardRepository.findById(cardId);
		if (card != null) {
			BigDecimal discountSize = card.getDiscountSize();
			totalCost = totalCost.multiply(discountSize).divide(BigDecimal.valueOf(100)).setScale(2,
							RoundingMode.HALF_EVEN);
		} else {
			totalCost.setScale(2, RoundingMode.HALF_EVEN);
		}
		return totalCost;
	}

	@Override
	public Check findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
