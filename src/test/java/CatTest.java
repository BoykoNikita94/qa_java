import com.example.Cat;
import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    @Mock
    Feline feline;

    @Test
    public void shouldReturnFoodType() throws Exception {
        List<String> food = List.of("Курица", "Свинина");
        Mockito.when(feline.eatMeat()).thenReturn(food);
        Cat cat = new Cat(feline);
        List<String> expected = cat.getFood();
        assertEquals(expected, food);
    }

    @Test
    public void shouldReturnSound() {
        Cat cat = new Cat(feline);
        String expected = "Мяу";
        String actual = cat.getSound();
        assertEquals(expected, actual);
    }
}