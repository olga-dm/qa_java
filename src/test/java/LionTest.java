import com.example.Cat;
import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionTest {
    private final String sex;
    private final boolean expectedMane;
    private final int expectedKittens;

     @Mock
    Feline feline;
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
        MockitoAnnotations.initMocks(this);
        Mockito.when(feline.getFood("Хищник")).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));
        Mockito.when(feline.getKittens()).thenReturn(1);
        lion = new Lion(sex, feline);
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
            new Lion("другое", feline);
        });

        String expectedMessage = "Используйте допустимые значения пола животного - самей или самка";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}