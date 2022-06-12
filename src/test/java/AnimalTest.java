import com.example.Animal;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Enclosed.class)
public class AnimalTest {

    @RunWith(Parameterized.class)
    public static class AnimalParameterizedTest {
        private final String animalKind;
        private final List expected;

        public AnimalParameterizedTest(String animalKind, List expected) {
            this.animalKind = animalKind;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static Object[][] getData() {
            return new Object[][]{
                    {"Травоядное", List.of("Трава", "Различные растения")},
                    {"Хищник", List.of("Животные", "Птицы", "Рыба")}
            };
        }

        @Test
        public void shouldReturnFoodType() throws Exception {
            Animal animal = new Animal();
            String actual = String.valueOf(animal.getFood(animalKind));
            assertEquals(expected.toString(), actual);
        }
    }

    public static class AnimalNotParameterizedTest {

        @Test
        public void shouldReturnFamily() {
            Animal animal = new Animal();
            String expected = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
            String actual = animal.getFamily();
            assertEquals(expected, actual);
        }

        @Test
        public void getFoodShouldThrowException() {
            Animal animal = new Animal();
            Exception exception = assertThrows(Exception.class, () -> {
                animal.getFood("test");
            });

            String expectedMessage = "Неизвестный вид животного, используйте значение Травоядное или Хищник";
            String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }
    }
}
