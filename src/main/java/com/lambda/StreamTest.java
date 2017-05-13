package com.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auth chenmingzhou
 */
public class StreamTest {

    public static void main(String[] args){
        test();
    }

    private static void test(){
        List<TestEntry> list = new ArrayList<>();
        list.add(TestEntry.valueOf("1","2","3","4"));
        list.add(TestEntry.valueOf("1","0","3","0"));
        list.add(TestEntry.valueOf("1","0","3","0"));

        Map<String,Map<String,List<TestEntry>>> map2;
        map2 = list.stream().collect(Collectors.groupingBy(TestEntry::getOne,Collectors.groupingBy(TestEntry::getTwo)));  //1，2 位相同
        map2 = list.stream().collect(Collectors.groupingBy(TestEntry::getOne,Collectors.groupingBy(TestEntry::getThree)));  //1，3 位相同
        map2 = list.stream().collect(Collectors.groupingBy(TestEntry::getOne,Collectors.groupingBy(TestEntry::getFour)));  //1，4 位相同
        map2 = list.stream().collect(Collectors.groupingBy(TestEntry::getTwo,Collectors.groupingBy(TestEntry::getThree)));  //2，3 位相同
        map2 = list.stream().collect(Collectors.groupingBy(TestEntry::getTwo,Collectors.groupingBy(TestEntry::getFour)));  //2，4 位相同
        map2 = list.stream().collect(Collectors.groupingBy(TestEntry::getThree,Collectors.groupingBy(TestEntry::getFour)));  //3，4 位相同


        Map<String,Map<String,Map<String,List<TestEntry>>>> map3;
        map3 = list.stream().collect(Collectors.groupingBy(TestEntry::getOne,Collectors.groupingBy(TestEntry::getTwo,Collectors.groupingBy(TestEntry::getThree))));  //1，2,3 位相同
        map3 = list.stream().collect(Collectors.groupingBy(TestEntry::getOne,Collectors.groupingBy(TestEntry::getTwo,Collectors.groupingBy(TestEntry::getFour))));  //1，2,4 位相同
        map3 = list.stream().collect(Collectors.groupingBy(TestEntry::getTwo,Collectors.groupingBy(TestEntry::getThree,Collectors.groupingBy(TestEntry::getFour))));  //2，3,4 位相同
        //map2
        map2.forEach((key,value)->{
            value.forEach((key2,value2)->{
                if(value2.size()>1){
                    System.out.println(key2+"=>"+value2);
                }
            });

        });
        //map3
        map3.forEach((key,value)->{
            value.forEach((key2,value2)->{
                value2.forEach((key3,value3) ->{

                });
            });

        });
    }

    static class TestEntry {

        private String one;
        private String two;
        private String three;
        private String four;

        public static TestEntry valueOf(String one, String two, String three, String four){
            TestEntry entry = new TestEntry();
            entry.setOne(one);
            entry.setTwo(two);
            entry.setThree(three);
            entry.setFour(four);
            return entry;
        }

        public String getOne() {
            return one;
        }

        public void setOne(String one) {
            this.one = one;
        }

        public String getTwo() {
            return two;
        }

        public void setTwo(String two) {
            this.two = two;
        }

        public String getThree() {
            return three;
        }

        public void setThree(String three) {
            this.three = three;
        }

        public String getFour() {
            return four;
        }

        public void setFour(String four) {
            this.four = four;
        }

        @Override
        public String toString() {
            return "one:"+one+",two:"+two+",three:"+three+",four:"+four;
        }
    }
}
