package main.java.test;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: 测试类
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/11/22 9:35
 * @Version: V1.0
 **/

public class Main {

    public static void main(String[] args) {
        // 测试菜单列表集合
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        // 代表例子
        List<String> threeHighCaloricDishName = menu.stream().filter(d -> d.getCalories() > 300)
                                                             .map(Dish::getName)
                                                             .limit(3).collect(Collectors.toList());
//        System.out.println(threeHighCaloricDishName);

        //① 筛选filter 和 切片limit/skip
        // 筛选：获取高卡路里的默认顺序前三个菜
        List<Dish> threeHighCaloricDish = menu.stream().filter(d -> d.getCalories() > 300)
                .limit(3).collect(Collectors.toList());
        // 截短流：获取高卡路里的默认顺序跳过前三个的剩下的菜
        List<Dish> lastLowCaloricDish = menu.stream().filter(d -> d.getCalories() > 300)
                .skip(3).collect(Collectors.toList());
        // 筛选：各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
//        numbers.stream()
//                .filter(i -> i % 2 == 0)
//                .distinct()
//                .forEach(System.out::println);
//        System.out.println(lastLowCaloricDish);

        // ② 映射 Map 和 flatMap：从某些对象中选择信息
        List<String> allDishNames = menu.stream().map(Dish::getName).collect(Collectors.toList());
        List<Integer> allDishNameLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        // flatMap扁平化几个单独的流为统一的流，从而能统一的distinct
        // 例子：将 单词列表["Hello", "World"]处理后返回
        // 各不同的字符 ["H","e","l","o","W","r","d"]
//        String[] words = {"Hello","World"};
//        List<String> wordList = Arrays.asList(words);
//        List<String> uniqueCharacters = wordList.stream().map(w -> w.split(""))
//                .flatMap(Arrays::stream)
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(uniqueCharacters);
//        // 查找是否存在 -终端操作 anyMatch()、allMatch()、noneMatch()，返回布尔值
//        boolean isVegetarianFriendly = menu.stream().anyMatch(Dish::isVegetarian);
//        boolean isAllVegetarianFriendly = menu.stream().allMatch(Dish::isVegetarian);
//        boolean isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
//
//        // 查找 findAny
//        Optional<Dish> anyOneVegetarianDish = menu.stream()
//                .filter(Dish::isVegetarian)
//                .findAny();
//        menu.stream()
//                .filter(Dish::isVegetarian)
//                .findAny()
//                .ifPresent(System.out::println);
        // 查找第一个 findFirst 用法与 findAny相同

        // 规约 reduce
        // 求和
        int sum = numbers.stream().reduce(0,(a,b) -> a + b);
        int sum2 = numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum);
        System.out.println("--------------");
        // 求最小值
        Optional<Integer> maximumValue = numbers.stream().reduce(Integer::max);
        System.out.println(maximumValue.get());
        Optional<Integer> minimumValue = numbers.stream().reduce(Integer::min);
        System.out.println(minimumValue.get());

        // map-reduce操作，求menu菜的总卡路里
        Integer totalCalor = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(totalCalor);

        // 对典型的三种数据 int double Long进行操作，可以用 mapToInt转换成 IntStream
        // mapToDouble转成 DoubleStream， mapToLong转成LongStream，而 IntStream还有sum,max,min,average等方便的方法
        int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories);
        OptionalDouble aveCalories = menu.stream().mapToInt(Dish::getCalories).average();
        // 如果没有值则有默认
        double aveCal = aveCalories.orElse(0.0);
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        System.out.println(totalCalories);
        // 数值范围 range不包括末尾，rangeClosed包括尾巴
        IntStream evenNumbers = IntStream.range(1, 100).filter(n -> n%2 == 0);
        System.out.println(evenNumbers.count());

        //此外还可以通过 字符串，数组，文件生成流
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        Stream<String> emptyStream = Stream.empty();

        int[] numberArr = {2, 3, 5, 7, 11, 13};
        int arrSum = Arrays.stream(numberArr).sum();
        //
        //
        //        //// ---------------用流收集数据 Collectors收集器
        Long howManyDishes = menu.stream().collect(Collectors.counting());
        long howManyDishes2 = menu.stream().count();
        // 查找最大值/最小值
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        // 汇总 summingInt Long/Double
        int totalCalories2 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        double avgCalories = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        // 统计值，一次返回 最大，最小，平均，总和，数量
        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        // 连接字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu);
        System.out.println("--------");
        // -----------分组---------
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);
        // 个性化分组



    }

}
