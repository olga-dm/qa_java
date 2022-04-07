import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    @Mock
    Feline feline;
    private Cat cat;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(feline.eatMeat()).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));
        cat = new Cat(feline);
    }

    @Test
    public void checkSoundCat() {
        assertEquals(cat.getSound(), "Мяу");
    }

    @Test
    public void checkFoodCat() throws Exception {
        List<String> eat = cat.getFood();
        assertEquals(eat, List.of("Животные", "Птицы", "Рыба"));
    }

    @Test
    public void isEatMeatMethodCallWithFelineArgument() throws Exception {
        cat.getFood();
        Mockito.verify(feline).eatMeat();
    }
}