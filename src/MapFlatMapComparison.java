import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MapFlatMapComparison {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "2", "a", "3");
        List<Optional<Integer>> integers = numbers.stream().map(MapFlatMapComparison::convertStringToNumber)
                .toList();
        System.out.println(integers);

        List<Integer> flatMapped = numbers
                .stream()
                .flatMap(item -> convertStringToNumber(item).stream())
                .toList();
        System.out.println(flatMapped);
    }

    static Optional<Integer> convertStringToNumber(String s){
        try{
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException ex){
            return Optional.empty();
        }
    }
}
