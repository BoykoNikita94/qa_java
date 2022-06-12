import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Enclosed.class)
public class LionTest {

    @RunWith(Parameterized.class)
    public static class LionParameterizedTest {
        private final String sex;
        private final boolean expected;

        @Mock
        Feline feline;

        public LionParameterizedTest(String sex, boolean expected) {
            this.sex = sex;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static Object[][] getData() {
            return new Object[][]{
                    {"Самец", true},
                    {"Самка", false}
            };
        }

        @Test
        public void shouldReturnLionSex() throws Exception {
            Lion lion = new Lion(sex, feline);
            boolean actual = lion.doesHaveMane();
            assertEquals(expected, actual);
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class LionNotParameterizedTest {

        @Mock
        Feline feline;

        @Test
        public void shouldReturnKittens() throws Exception {
            int expected = 1;
            Mockito.when(feline.getKittens()).thenReturn(expected);
            Lion lion = new Lion("Самец", feline);
            int actual = lion.getKittens();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnFoodType() throws Exception {
            List<String> expected = List.of("Мясо", "Птица");
            Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Мясо", "Птица"));
            Lion lion = new Lion("Самец", feline);
            List<String> actual = lion.getFood();
            assertEquals(expected, actual);
        }

        @Test
        public void lionShouldThrowException() {
            Exception exception = assertThrows(Exception.class, () -> {
                new Lion("test", feline);
            });

            String expectedMessage = "Используйте допустимые значения пола животного - самец или самка";
            String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }
    }
}
