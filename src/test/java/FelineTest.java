import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineTest {
    private final int kittensCount;
    private final int expected;
    private Feline feline;

    public FelineTest(int kittensCount, int expected) {
        this.kittensCount = kittensCount;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getKittensCount() {
        return new Object[][]{
                {1, 1},
                {0, 0},
                {3, 3}
        };
    }

    @Before
    public void init() {
        feline = new Feline();
    }

    @Test
    public void checkEatMeatFeline() throws Exception {
        List<String> eat = feline.eatMeat();
        assertEquals(eat, List.of("Животные", "Птицы", "Рыба"));
    }

    @Test
    public void checkFamilyFeline() {
        assertEquals(feline.getFamily(), "Кошачьи");
    }

    @Test
    public void checkKittens() {
        assertEquals(feline.getKittens(), 1);
    }


    @Test
    public void testGetKittens() {
        int actual = feline.getKittens(kittensCount);
        assertEquals(expected, actual);
    }
}