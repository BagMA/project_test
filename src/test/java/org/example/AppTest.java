package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Unit test for simple App.
 * Метод inputTest() проверяет не является ли возвращаемый список пустым.
 * Метод outputTest() проверяет возвращаемое значение на соответствие значению переданному в параметре методу.
 * Метод sortMethodTest() правильность логики метода sortMethod(). Требует ручного ввода значений списка и знания expected-значения.
 * Метод sortMethod2Test() проверяет работу метода sortMethod() на произвольных наборах данных.Требует знания expected-значения.
 */
public class AppTest 
{
    App app = new App();

    @org.junit.jupiter.api.Test
    public void inputTest() {
        List<Integer> l = app.input();
        Assertions.assertFalse(l.isEmpty());
    }


    @ParameterizedTest
    @ValueSource(ints = 2)
    public void outputTest(int num) {
        app.output(num);
        Assertions.assertEquals(num, 2);
    }

    @Test
    public void sortMethodTest() {
        long start = Runtime.getRuntime().freeMemory();
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(10);
        list.add(1);
        list.add(4);
        list.add(7);
        list.add(8);
        list.add(2);
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(3);
        list.add(1);

        int x = app.sortMethod(list);
        long end = Runtime.getRuntime().freeMemory();
        long memoTaken = (start - end) / 1048576;
        System.out.println("sortMethods memory = " + memoTaken+ "mB");
        Assertions.assertEquals(7, x);
    }


    @Test
    public void sortMethod2Test(){
        long start = Runtime.getRuntime().freeMemory();
        List<Integer> list = new ArrayList<>();
        int nullNum = 3000;
        int oneNum = 10000;
        list.add(nullNum);
        list.add(oneNum);
        Random random = new Random(1);
        for (int i = 2; i < oneNum; i++) {
            int a = random.nextInt(6000);
            list.add(a);
        }
        int x = app.sortMethod(list);
        long end = Runtime.getRuntime().freeMemory();
        long memoTaken = (start - end) / 1048576;
        System.out.println("sortMethodsBigData memory = " + memoTaken+ "mB");
        Assertions.assertEquals(4880, x);
    }
}
