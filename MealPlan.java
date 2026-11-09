package com.thanu.dietreminder;

/** 30-day meal plan + reminder slots. Edit dishes or times here. */
public final class MealPlan {
    private MealPlan() {}

    // Reminder slots: hour, minute, title. Index used as notification id.
    public static final int[][] TIMES = {
        {6, 30}, {8, 0}, {11, 0}, {13, 30}, {16, 30}, {19, 30}, {21, 30}
    };
    public static final String[] TITLES = {
        "Wake-up drink", "Breakfast", "Mid-morning", "Lunch", "Evening snack", "Dinner", "Bedtime"
    };

    /** PLAN[day][0..4] = breakfast, mid-morning, lunch, evening snack, dinner */
    public static final String[][] PLAN = {
        {"2 idli + drumstick sambar + mint chutney + 2 boiled eggs", "1 Guava + buttermilk", "1 cup red rice + drumstick sambar + beans poriyal + curd", "Green tea + roasted chana", "2 chapati + dal tadka + mixed veg sabzi"},
        {"1 ragi dosa + tomato chutney + 1 cup sprouts salad", "1 Apple + buttermilk", "1 cup millet (samai) rice + vatha kuzhambu (light) + beetroot poriyal + curd", "Buttermilk + 1 handful peanuts", "Grilled chicken (100 g) + sautéed vegetables + 1 chapati"},
        {"Vegetable oats upma + coconut-less peanut chutney + 1 boiled egg", "1 Orange + buttermilk", "1 cup rice + fish fry (tawa, ayala) + dal + spinach poriyal", "Green tea + sprouts sundal", "Paneer & vegetable salad + 1 cup dal soup"},
        {"2 pesarattu (moong dosa) + ginger chutney", "1 Pomegranate + buttermilk", "1 cup brown rice + chicken pepper fry (dry) + cucumber raita + rasam", "1 guava + 5 almonds", "2 millet roti + paneer bhurji + green salad"},
        {"Vegetable poha + 1 cup curd + 5 almonds", "1 Papaya (1 cup) + buttermilk", "1 cup brown rice + fish curry (mathi) + cabbage poriyal + rasam", "Coconut water + roasted makhana", "Vegetable daliya khichdi + curd"},
        {"2 egg-white omelette + 1 wheat dosa + tomato chutney", "1 Pear + buttermilk", "1 cup brown rice + chicken curry (no skin, less oil) + vegetable salad + rasam", "Green tea + 1 boiled sweet corn (small)", "2 ragi roti + egg bhurji (2 eggs) + cucumber salad"},
        {"Ragi kanji (no sugar) + 1 cup boiled chana sundal", "1 Watermelon (1 cup) + buttermilk", "Vegetable khichdi (dal + rice) + curd + papad (roasted)", "1 orange + pumpkin seeds (1 tbsp)", "Moong dal soup + 2 phulka + lauki (sorakkai) kootu"},
        {"2 millet (thinai) idli + onion sambar + 1 boiled egg", "1 Guava + buttermilk", "1 cup rice + mor kootu + karela (pavakkai) poriyal + dal", "Green tea + roasted chana", "2 ragi dosa + peanut chutney + sprouts salad"},
        {"Rava-vegetable upma (small) + mint chutney + 1 fruit", "1 Apple + buttermilk", "2 chapati + rajma masala + cucumber raita + carrot salad", "Buttermilk + 1 handful peanuts", "2 idli + sambar + 1 boiled egg"},
        {"2 adai (mixed dal dosa) + avial (1 small cup)", "1 Orange + buttermilk", "2 chapati + palak paneer (low oil) + onion-tomato salad", "Green tea + sprouts sundal", "2 chapati + fish curry (small piece) + beans poriyal"},
        {"Vegetable daliya (broken wheat) + 1 cup curd", "1 Pomegranate + buttermilk", "2 chapati + chana masala + bhindi fry (low oil) + buttermilk", "1 guava + 5 almonds", "Vegetable clear soup + 1 wheat dosa + tomato chutney"},
        {"2 kuzhi paniyaram (ragi) + peanut chutney + 1 boiled egg", "1 Papaya (1 cup) + buttermilk", "Lemon millet rice (small) + mixed veg sambar + carrot-beans poriyal + curd", "Coconut water + roasted makhana", "2 wheat dosa + vegetable kurma (light)"},
        {"1 besan chilla + green chutney + 1 cup buttermilk", "1 Pear + buttermilk", "1 cup rice + keerai kootu + egg curry (2 eggs) + rasam", "Green tea + 1 boiled sweet corn (small)", "2 chapati + dal tadka + mixed veg sabzi"},
        {"2 idli + vegetable kurma (light) + 2 boiled eggs", "1 Watermelon (1 cup) + buttermilk", "1 cup red rice + moru kuzhambu + avarakkai poriyal + sundal", "1 orange + pumpkin seeds (1 tbsp)", "Grilled chicken (100 g) + sautéed vegetables + 1 chapati"},
        {"Moong dal chilla + tomato chutney + 5 almonds", "1 Guava + buttermilk", "1 cup red rice + kollu (horsegram) rasam + pumpkin kootu + boiled egg", "Green tea + roasted chana", "Paneer & vegetable salad + 1 cup dal soup"},
        {"2 idli + drumstick sambar + mint chutney + 2 boiled eggs", "1 Apple + buttermilk", "1 cup red rice + drumstick sambar + beans poriyal + curd", "Buttermilk + 1 handful peanuts", "2 millet roti + paneer bhurji + green salad"},
        {"1 ragi dosa + tomato chutney + 1 cup sprouts salad", "1 Orange + buttermilk", "1 cup millet (samai) rice + vatha kuzhambu (light) + beetroot poriyal + curd", "Green tea + sprouts sundal", "Vegetable daliya khichdi + curd"},
        {"Vegetable oats upma + coconut-less peanut chutney + 1 boiled egg", "1 Pomegranate + buttermilk", "1 cup rice + fish fry (tawa, ayala) + dal + spinach poriyal", "1 guava + 5 almonds", "2 ragi roti + egg bhurji (2 eggs) + cucumber salad"},
        {"2 pesarattu (moong dosa) + ginger chutney", "1 Papaya (1 cup) + buttermilk", "1 cup brown rice + chicken pepper fry (dry) + cucumber raita + rasam", "Coconut water + roasted makhana", "Moong dal soup + 2 phulka + lauki (sorakkai) kootu"},
        {"Vegetable poha + 1 cup curd + 5 almonds", "1 Pear + buttermilk", "1 cup brown rice + fish curry (mathi) + cabbage poriyal + rasam", "Green tea + 1 boiled sweet corn (small)", "2 ragi dosa + peanut chutney + sprouts salad"},
        {"2 egg-white omelette + 1 wheat dosa + tomato chutney", "1 Watermelon (1 cup) + buttermilk", "1 cup brown rice + chicken curry (no skin, less oil) + vegetable salad + rasam", "1 orange + pumpkin seeds (1 tbsp)", "2 idli + sambar + 1 boiled egg"},
        {"Ragi kanji (no sugar) + 1 cup boiled chana sundal", "1 Guava + buttermilk", "Vegetable khichdi (dal + rice) + curd + papad (roasted)", "Green tea + roasted chana", "2 chapati + fish curry (small piece) + beans poriyal"},
        {"2 millet (thinai) idli + onion sambar + 1 boiled egg", "1 Apple + buttermilk", "1 cup rice + mor kootu + karela (pavakkai) poriyal + dal", "Buttermilk + 1 handful peanuts", "Vegetable clear soup + 1 wheat dosa + tomato chutney"},
        {"Rava-vegetable upma (small) + mint chutney + 1 fruit", "1 Orange + buttermilk", "2 chapati + rajma masala + cucumber raita + carrot salad", "Green tea + sprouts sundal", "2 wheat dosa + vegetable kurma (light)"},
        {"2 adai (mixed dal dosa) + avial (1 small cup)", "1 Pomegranate + buttermilk", "2 chapati + palak paneer (low oil) + onion-tomato salad", "1 guava + 5 almonds", "2 chapati + dal tadka + mixed veg sabzi"},
        {"Vegetable daliya (broken wheat) + 1 cup curd", "1 Papaya (1 cup) + buttermilk", "2 chapati + chana masala + bhindi fry (low oil) + buttermilk", "Coconut water + roasted makhana", "Grilled chicken (100 g) + sautéed vegetables + 1 chapati"},
        {"2 kuzhi paniyaram (ragi) + peanut chutney + 1 boiled egg", "1 Pear + buttermilk", "Lemon millet rice (small) + mixed veg sambar + carrot-beans poriyal + curd", "Green tea + 1 boiled sweet corn (small)", "Paneer & vegetable salad + 1 cup dal soup"},
        {"1 besan chilla + green chutney + 1 cup buttermilk", "1 Watermelon (1 cup) + buttermilk", "1 cup rice + keerai kootu + egg curry (2 eggs) + rasam", "1 orange + pumpkin seeds (1 tbsp)", "2 millet roti + paneer bhurji + green salad"},
        {"2 idli + vegetable kurma (light) + 2 boiled eggs", "1 Guava + buttermilk", "1 cup red rice + moru kuzhambu + avarakkai poriyal + sundal", "Green tea + roasted chana", "Vegetable daliya khichdi + curd"},
        {"Moong dal chilla + tomato chutney + 5 almonds", "1 Apple + buttermilk", "1 cup red rice + kollu (horsegram) rasam + pumpkin kootu + boiled egg", "Buttermilk + 1 handful peanuts", "2 ragi roti + egg bhurji (2 eggs) + cucumber salad"}
    };

    public static final String WAKE = "Warm water + 5 soaked almonds + 2 walnuts";
    public static final String BED = "Warm turmeric milk (no sugar). Log today: cigarettes, water, walk, weight.";

    /** Text for a reminder slot on a given plan day (0-29). */
    public static String textFor(int slot, int day) {
        if (slot == 0) return WAKE;
        if (slot == 6) return BED;
        return PLAN[day % 30][slot - 1];
    }
}
