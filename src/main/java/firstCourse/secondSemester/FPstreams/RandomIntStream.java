package firstCourse.secondSemester.FPstreams;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomIntStream {
    private static final int MAX_NUMBER_NOT_REACHED = 1000001;
    private static final double FOR_HONOR_RANDOM = 0.5;

    public static void main(String[] args) {
        int n = 10;

        Stream.Builder<Integer> someStreamBuilder = Stream.builder();

        while (n > 0) {
            someStreamBuilder.add((int) Math.round(Math.random() * (MAX_NUMBER_NOT_REACHED) - FOR_HONOR_RANDOM));
            n--;
        }

        Collection<Integer> someCol = someStreamBuilder.build()
                .filter((g) -> Math.ceil(Math.log10(g)) % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(someCol);
        System.out.println(someCol.stream().count());

        System.out.println(someStreamBuilder.build()
                .filter((g) -> Math.ceil(Math.log10(g)) % 2 == 0)
                .count());
    }
}
