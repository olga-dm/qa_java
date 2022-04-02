import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LionTest {
    private final String sex;
    private final boolean expectedMane;
    private final int expectedKittens;
    private Lion lion;

    public LionTest(String sex, boolean expectedMane, int expectedKittens) {
        this.sex = sex;
        this.expectedMane = expectedMane;
        this.expectedKittens = expectedKittens;
    }

    @Parameterized.Parameters
    public static Object[][] getLion() {
        return new Object[][]{
                {"Самка", false, 1},
                {"Самец", true, 1},
        };
    }

    @Before
    public void init() {
        try {
            lion = new Lion(sex, new Feline());
        } catch (Exception ex) {
            Assert.assertEquals(ex.getMessage(), "Используйте допустимые значения пола животного - самей или самка");
        }
    }

    @Test
    public void checkKittens() {
        assertEquals(lion.getKittens(), expectedKittens);
    }


    @Test
    public void checkDoesHaveMane() {
        assertEquals(lion.doesHaveMane(), expectedMane);
    }

    @Test
    public void checkFoodLion() {
        try {
            List<String> eat = lion.getFood();
            assertEquals(eat, List.of("Животные", "Птицы", "Рыба"));
        } catch (Exception ex) {
            Assert.assertNotEquals(ex, null);
        }
    }
}