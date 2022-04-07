import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;

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
    public void init() throws Exception {
        lion = new Lion(sex, new Feline());
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
    public void checkFoodLion() throws Exception {
        List<String> eat = lion.getFood();
        assertEquals(eat, List.of("Животные", "Птицы", "Рыба"));
    }

    @Test
    public void checkException() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("другое", new Feline());
        });

        String expectedMessage = "Используйте допустимые значения пола животного - самей или самка";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}