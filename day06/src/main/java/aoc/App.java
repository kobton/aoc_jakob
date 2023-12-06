package aoc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
public class App {
    private final List<String> input;
    public App(List<String> input) {
        this.input = input;
    }
    public long getSolutionPart1() {
        long[] times = Arrays.stream(input.get(0).replaceAll("Time:        ", "").split("     ")).mapToLong(Long::valueOf).toArray();
        long[] distances = Arrays.stream(input.get(1).replaceAll("Distance:   ", "").split("   ")).mapToLong(Long::valueOf).toArray();
        return calculateDist(times, distances);
    }
    public long getSolutionPart2() {
        long[] times = Arrays.stream(input.get(0).replaceAll("Time:        ", "").split("     ")).mapToLong(Long::valueOf).toArray();
        long[] time = createOneElementArray(times);
        long[] distances = Arrays.stream(input.get(1).replaceAll("Distance:   ", "").split("   ")).mapToLong(Long::valueOf).toArray();
        long[] distance = createOneElementArray(distances);
        return calculateDist(time, distance);
    }
    private long[] createOneElementArray(long[] times){
        String stringTime = "";
        for(long time : times){
            stringTime = stringTime+String.valueOf(time);
        }
        long[] array = {Long.valueOf(stringTime)};
        return array;
    }
    private long calculateDist(long[] times, long[] distances){
        List<List<Long>> added = new ArrayList<>();
        int a;
        for(a=0 ; a<times.length; a++){
                long i;
                List<Long> winnerInstance = new ArrayList<>();
                for(i=1 ; i<times[a]; i++){
                    long mydist = i*(times[a] - i);
                    winnerInstance.add(mydist);
                }
            int finalA = a;
            List<Long> winnerInstances = winnerInstance.stream().filter(x -> (x>distances[finalA])).collect(Collectors.toList());
            added.add(winnerInstances);
        }
        return added.stream().mapToLong(x -> x.size()).reduce(1, (c, b) -> c * b);
    }
    public static void main(String[] args) throws IOException {
        List<String> input = parseInput("input.txt");
        String part = System.getenv("part") == null ? "part1" : System.getenv("part");
        if (part.equals("part2"))
            System.out.println(new App(input).getSolutionPart2());
        else
            System.out.println(new App(input).getSolutionPart1());
    }
    private static List<String> parseInput(String filename) throws IOException {
        return Files.lines(Path.of(filename))
                .collect(Collectors.toList());
    }
}
