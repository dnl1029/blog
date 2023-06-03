package com.example.blog.service;

import com.example.blog.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
@Slf4j
public class StreamService {

    public void executeStream() {
        //1단계 : stream 생성
        // 1) array 생성
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 1~2 요소 [b, c]
        log.info("1) Arrays.stream(arr) : {}",stream);
        log.info("1) Arrays.stream(arr, 1, 3) : {}",streamOfArrayPart);

        // 2) list 생성
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream2 = list.stream();
        Stream<String> parallelStream = list.parallelStream();
        log.info("2) list.stream() : {}",stream2);
        log.info("2) list.parallelStream() : {}",parallelStream);

        // 3) 수열 생성
        Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5);
        // [30, 32, 34, 36, 38]
        log.info("3) 수열 생성 : {}",iteratedStream);

        //2단계 : 가공하기
        // 4) 이름이 들어간 예제 list
        List<String> names = Arrays.asList("Eric", "Elena", "Java");
        log.info("4) 이름이 들어간 예제 list : {}",names);

        // 5) filter예시, a가 들어간 이름이 들어간 스트림만 리턴함
        // [Elena, Java]
        Stream<String> stream3 = names.stream().filter(name -> name.contains("a"));
        //아래와 같이 작성해도 작동
        Stream<String> streamnew = names.stream().filter(i -> i.contains("a"));
        log.info("5) filter예시, a가 들어간 이름이 들어간 스트림만 리턴함 : {}",stream3);
        log.info("5) filter예시, a가 들어간 이름이 들어간 스트림만 리턴함, i로변경 : {}",streamnew);

        // 6) map예시, 대문자로 변경
        // [ERIC, ELENA, JAVA]
        Stream<String> stream4 = names.stream().map(String::toUpperCase);
        log.info("6) map예시, 대문자로 변경 : {}",stream4);

        //아래와 같이 상품을 상품수량 과 같이 다른 타입으로 mapping 도 가능
        //    Stream<Integer> stream =
        //            productList.stream()
        //                    .map(Product::getAmount);
        // [23, 14, 13, 23, 13]

        // 7) 다중 list를 stream 으로 푸는 예시 [[a], [b]] -> // [a, b]
        List<List<String>> list2 = Arrays.asList(Arrays.asList("a"),Arrays.asList("b"));
        log.info("7) 다중 list 예시 [[a], [b]] : {}",list2);
        List<String> flatList = list2.stream().flatMap(java.util.Collection::stream).collect(Collectors.toList());
        log.info("7) 다중 list를 flatMap으로 풀었을떄 : {}",flatList);

        //학생 객체를 가진 스트림에서 학생의 국영수 점수를 뽑아 새로운 스트림을 만들어 평균을 구하는 코드입니다. 이는 map 메소드 자체만으로는 한번에 할 수 없는 기능입니다.
//        students.stream()
//                .flatMapToInt(student ->
//                IntStream.of(student.getKor(),
//                student.getEng(),
//                student.getMath()))
//                .average().ifPresent(avg ->
//                System.out.println(Math.round(avg * 10)/10.0));

        // 8) sorting 예시 list
        List<String> lang = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");
        log.info("8) sorting 예시 list : {}",lang);

        // 9) sorting을 Comparator 생략(default 오름차순)
        // [Go, Groovy, Java, Python, Scala, Swift]
        List<String> collect = lang.stream().sorted().collect(Collectors.toList());
        log.info("9) 9) sorting을 Comparator 생략(default 오름차순) : {}",collect);

        // 10) sorting, 내림차순
        // [Swift, Scala, Python, Java, Groovy, Go]
        List<String> collect2 = lang.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        log.info("10) sorting, 내림차순 : {}",collect2);

        // 11) sorting, 문자열 기준으로 정렬 방법 1
        // [Go, Java, Scala, Swift, Groovy, Python]
        List<String> collect3 = lang.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        log.info("11) sorting, 문자열 기준으로 정렬 방법 1 : {}",collect3);

        // 12) sorting, 문자열 기준으로 정렬 방법 2, reverse
        // [Groovy, Python, Scala, Swift, Java, Go]
        List<String> collect4 = lang.stream().sorted((s1, s2) -> s2.length() - s1.length()).collect(Collectors.toList());
        log.info("12) sorting, sorting, 문자열 기준으로 정렬 방법 2, reverse : {}",collect4);

        //처리하는 중간에 결과 확인할 때만
//        int sum = IntStream.of(1, 3, 5, 7, 9)
//                .peek(System.out::println)
//                .sum();


        // 3단계 결과 만들기
        // 13) count
        long count = IntStream.of(1, 3, 5, 7, 9).count();
        log.info("13) count : {}",count);

        // 14) sum
        long sum = IntStream.of(1, 3, 5, 7, 9).sum();
        log.info("14) sum : {}",sum);

        // 15) average
        // 만약 스트림이 비어 있는 경우, 평균최대최소는 표현방법이 없어 Optional
        OptionalDouble average = IntStream.of(1, 3, 5, 7, 9).average();
        log.info("15) average : {}",average);

        // 16) min
        OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
        log.info("16) min : {}",min);

        // 17) max
        OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();
        log.info("17) max : {}",max);

        // 18) Reduce, 인자가 한개일때 sum하는 방법
        OptionalInt reduced =
                IntStream.range(1, 4) // [1, 2, 3]
                        .reduce((a, b) -> {
                            return Integer.sum(a, b);
                        });
        log.info("18) Reduce, 인자가 한개일때 sum하는방법 : {}",reduced);

        // 19) Reduce, 인자가 두개일때 sum하는 방법, 처음 값 10은 초기값(identity)
        int reducedTwoParams =
                IntStream.range(1, 4) // [1, 2, 3]
                        .reduce(10, Integer::sum); // method reference
        log.info("19) Reduce, 인자가 두개일때 sum하는 방법 : {}",reducedTwoParams);

        // 20) 세개의 인자를 받을때 sum하는 방법, 인자가 세개일때는 병렬처리해야함(parallelStream)
        Integer reducedParallel = Arrays.asList(1, 2, 3)
                .parallelStream()
                .reduce(10,
                        Integer::sum,
                        (a, b) -> {
                            return a + b;
                        });
        log.info("20) Reduce, 인자가 세개일때 sum하는 방법 : {}",reducedParallel);

        // 21) Collector를 위한 produce 객체 list 생성, amount와 name을 변수로 가지고 있음
        List<Product> productList =
                Arrays.asList(new Product(23, "potatoes"),
                        new Product(14, "orange"),
                        new Product(13, "lemon"),
                        new Product(23, "bread"),
                        new Product(13, "sugar"));
        log.info("21) Collector를 위한 produce 객체 list 생성 : {}",productList);

        // 22) map으로 name을 뽑고, .collect(Collectors.toList())를 활용하여 map 결과물을 리스트로 담음
        // [potatoes, orange, lemon, bread, sugar]
        List<String> collectorCollection =
                productList.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());
        log.info("22) map으로 name을 뽑고, .collect(Collectors.toList())를 활용하여 map 결과물을 리스트로 담음 : {}",collectorCollection);

        // 23) .collect(Collectors.joining())을 통해서 뽑은 string을 이어붙일수도 있음
        // potatoesorangelemonbreadsugar
        String listToString =
                productList.stream()
                        .map(Product::getName)
                        .collect(Collectors.joining());
        log.info("23) .collect(Collectors.joining())을 통해서 뽑은 string을 이어붙일수도 있음 : {}",listToString);

        // 24) .collect(Collectors.joining())에서 인자세개 받을때, 처음은 인자 구분자, 그다음은 앞에 넣는 값, 마지막에 넣는 값 순서
        // <potatoes, orange, lemon, bread, sugar>
        String listToString2 =
                productList.stream()
                        .map(Product::getName)
                        .collect(Collectors.joining(", ", "<", ">"));
        log.info("24) .collect(Collectors.joining())에서 인자세개 받을때 : {}",listToString2);

        // 25) collector에서 average 구하는 법
        Double averageAmount =
                productList.stream()
                        .collect(Collectors.averagingInt(Product::getAmount));
        log.info("25) collector에서 average 구하는 법 : {}",averageAmount);

        // 26) collector에서 sum 구하는 법
        Integer summingAmount =
                productList.stream()
                        .collect(Collectors.summingInt(Product::getAmount));
        log.info("26) collector에서 sum 구하는 법 : {}",summingAmount);

        // 27) int stream으로 바꿔주는 mapToInt 사용하여 기존 .sum() 방식 사용
        Integer summingAmount2 =
                productList.stream()
                        .mapToInt(Product::getAmount)
                        .sum();
        log.info("27) int stream으로 바꿔주는 mapToInt 사용하여 기존 .sum() 방식 사용 : {}",summingAmount2);

        // 28) collector에서 count, sum, avg, min, max 모두 담고있는 정보. map 사용필요없음
        IntSummaryStatistics statistics =
                productList.stream()
                        .collect(Collectors.summarizingInt(Product::getAmount));
        log.info("28) collector에서 count, sum, avg, min, max 모두 담고있는 정보. map 사용필요없음 : {}",statistics);

        // 29) collector에서 요소들을 그룹핑하는 방법
        Map<Integer, List<Product>> collectorMapOfLists =
                productList.stream()
                        .collect(Collectors.groupingBy(Product::getAmount));
        log.info("29) collector에서 요소들을 그룹핑하는 방법 : {}",collectorMapOfLists);

        // 30) collector에서 특정값 기준으로 요소를 그룹핑하여 boolean으로 구별하는방법
        Map<Boolean, List<Product>> mapPartitioned =
                productList.stream()
                        .collect(Collectors.partitioningBy(el -> el.getAmount() > 15));
        log.info("30) collector에서 특정값 기준으로 요소를 그룹핑하여 boolean으로 구별하는방법 : {}",mapPartitioned);

        // 31) forEach. 요소를 돌면서 실행되는 최종 작업
        productList.stream().forEach(i -> i.getName());


        // 32) findFirst()후 stream이 닫혀 재사용 불가한 예시
        Stream<String> samplestream =
                Stream.of("Eric", "Elena", "Java")
                        .filter(name -> name.contains("a"));

        Optional<String> firstElement = samplestream.findFirst();
        //findFirst()하면 스트림이 닫히기때문에 재사용 불가
        //Optional<String> anyElement = samplestream.findAny();
        log.info("32) findFirst()후 stream이 닫혀 재사용 불가한 예시 : {}",firstElement);

        // 33) stream을 collector로 list에 담으면 재사용 가능함
        List<String> samplename =
                Stream.of("Eric", "Elena", "Java")
                        .filter(name -> name.contains("a"))
                        .collect(Collectors.toList());

        Optional<String> firstElement2 = samplename.stream().findFirst();
        Optional<String> anyElement = samplename.stream().findAny();
        log.info("33) stream을 collector로 list에 담으면 재사용 가능함 : {}",firstElement2);
        log.info("33) stream을 collector로 list에 담으면 재사용 가능함2 : {}",anyElement);

        // 34) Null로 들어와도 방어할수 있는 Stream 예시
        List<String> nullList = null;
//        List<Integer> result = nullList.stream()
//                .filter(str -> str.contains("a"))
//                .map(String::length)
//                .collect(Collectors.toList());

        //collectionToStream 메서드를 활용하여 null에 안전한 stream 생성
        List<Integer> result2 = collectionToStream(nullList)
                .filter(str -> str.contains("a"))
                .map(String::length)
                .collect(Collectors.toList());
        log.info("34) Null로 들어와도 방어할수 있는 Stream 예시 : {}",result2);

    }

    public <T> Stream<T> collectionToStream(Collection<T> collection) {
        return Optional
                .ofNullable(collection)
                .map(java.util.Collection::stream)
                .orElseGet(Stream::empty);
    }

}

