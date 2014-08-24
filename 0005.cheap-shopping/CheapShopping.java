import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Google In-Person interview task
 * 
 * You are given a list of items / combo_items with their price list. And you
 * are given list of items to buy. Now you are asked to find which combination
 * to buy so that it costs you minimum. It doesnt matter if you are getting some
 * extra items if it costs less.
 * 
 * 
 * SN Price | Items/Combo_Items 1. 5 | Burger 2. 4 | French_Frice 3. 8 |
 * Coldrink 4. 12 | Burger, French_Frice, Coldrink 5. 14 | Burger, Coldrink
 * 
 * 
 * Input Items to Buy: Coldrink
 * 
 * Output(Sr.No) 3
 * 
 * Input Items to Buy: Burger Coldrink
 * 
 * Output(Sr.No) 4
 * 
 * Input Items to Buy: Burger French_Frice
 * 
 * Output(Sr.No) 1,2
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class CheapShopping {

	public static void main(String[] args) {

		Map<String, Integer> prices = new HashMap<String, Integer>();
		prices.put("1.Burger", 5);
		prices.put("2.French_Frice", 4);
		prices.put("3.Coldrink", 8);
		prices.put("4.Burger, French_Frice, Coldrink", 12);
		prices.put("5.Burger, Coldrink", 14);

		System.out.println(calculateCheapest(prices, 
				new String[] { "Burger", "Coldrink" }));
		
		System.out.println(calculateCheapest(prices, 
				new String[] { "Burger", "French_Frice" }));
		
		System.out.println(calculateCheapest(prices, 
				new String[] { "Coldrink" }));

	}

	private static String calculateCheapest(Map<String, Integer> prices,
			String[] toBuy) {

		String pack = "";
		int minPrice = Integer.MAX_VALUE;

		int cartPrice = 0, itemsInCart = 0;
		String tempCart = "";
		
		/* going through all the sets */
		for (String label : prices.keySet()) {

			for (String labelToBuy : toBuy) {
				
				/* set contains thing that we need? ok, put it in cart */
				if (label.contains(labelToBuy)) {
					
					/* change the cart only if set doesn't exists yet */
					if (!tempCart.contains(label)) {
						tempCart += label.concat(" ");
						cartPrice += prices.get(label);
					}
					
					/* but anyway mark our product as bought */
					itemsInCart++;
				} 
			}
			
			/* found the cheapest set? ok, let's go home then */
			if (itemsInCart == toBuy.length && minPrice > cartPrice) {
				minPrice = cartPrice;
				pack = tempCart;
				itemsInCart = 0;
				cartPrice = 0;
				tempCart = "";
			}

		}

		return pack;
	}
}
