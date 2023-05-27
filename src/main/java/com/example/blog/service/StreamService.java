package com.example.blog.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
public class StreamService {

    //1단계 : stream 생성

    String[] arr = new String[]{"a", "b", "c"};
    Stream<String> stream = Arrays.stream(arr);
    Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 1~2 요소 [b, c]



    List<String> list = Arrays.asList("a", "b", "c");
    Stream<String> stream2 = list.stream();
    Stream<String> parallelStream = list.parallelStream();



    //2단계 가공하기
    List<String> names = Arrays.asList("Eric", "Elena", "Java");

    //filter : a가 들어간 이름이 들어간 스트림만 리턴함
    Stream<String> stream3 =
            names.stream()
                    .filter(name -> name.contains("a"));
                    // [Elena, Java]

    //map
    Stream<String> stream4 =
            names.stream()
                    .map(String::toUpperCase);
                    // [ERIC, ELENA, JAVA]

    //아래와 같이 상품을 상품수량 과 같이 다른 타입으로 mapping 도 가능
//    Stream<Integer> stream =
//            productList.stream()
//                    .map(Product::getAmount);
// [23, 14, 13, 23, 13]

    List<List<String>> list2 = Arrays.asList(Arrays.asList("a"),Arrays.asList("b"));
                    // [[a], [b]]

    //오류뜨네
//    List<String> flatList = list2.stream().flatMap(Collection::stream).collect(Collectors.toList());


    // [a, b]

    //학생 객체를 가진 스트림에서 학생의 국영수 점수를 뽑아 새로운 스트림을 만들어 평균을 구하는 코드입니다. 이는 map 메소드 자체만으로는 한번에 할 수 없는 기능입니다.
//    students.stream()
//            .flatMapToInt(student ->
//            IntStream.of(student.getKor(),
//            student.getEng(),
//            student.getMath()))
//            .average().ifPresent(avg ->
//            System.out.println(Math.round(avg * 10)/10.0));

    List<String> lang = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

    List<String> newlist = null;
    Stream<String> stream5 = (Stream<String>) lang.stream().sorted().collect(Collectors.toList());
    // [Go, Groovy, Java, Python, Scala, Swift]

    Stream<String> stream6 = (Stream<String>) lang.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    // [Swift, Scala, Python, Java, Groovy, Go]

    Stream<String> stream7 = (Stream<String>) lang.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
    // [Go, Java, Scala, Swift, Groovy, Python]

    Stream<String> stream8 = (Stream<String>) lang.stream().sorted((s1, s2) -> s2.length() - s1.length()).collect(Collectors.toList());
    // [Groovy, Python, Scala, Swift, Java, Go]

    //처리하는 중간에 결과 확인할 때만
    int sum = IntStream.of(1, 3, 5, 7, 9)
            .peek(System.out::println)
            .sum();


    //3단계 결과만들기


}

