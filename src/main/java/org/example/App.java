package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Данные из файла input.txt считываются в методе input(). В нем же происходит добавление
 * значений из файла в список list. Основная логика программы содержится в методе sortMethod().
 * На входе он принимает список переданный ему методом input(). Сначала инициализируются переменные n - количество
 * запросов, которое может быть закэшировано на сервере, и m - количество запросов. Проверяется соответствие этих
 * переменных условию задания. Создается коллекция set для закешированных значений. В основном цикле содержится
 * оператор выбора для значений из списка list, который определяет достигла ли куча set максимального значения и
 * содержит ли она текущее значение цикла. Если set достиг максимального размера и не содержит
 * проверяемый элемент, то создается итератор для кучи set. Каждое значение set проверяется от текущего индекса
 * и до конца списка list будет ли оно повторяться. Если нет, то заменяется на текущее значение из основного цикла.
 * Переменная counter показывает количество обращений к серверу, её возвращает метод sortMethod(). Метод output()
 * в параметре принимает переменную counter и записывает её в файл output.txt.
 *
 */
public class App 
{
    private int counter;
    private int n;
    private int m;

    public List input (){
        List<Integer> list = new ArrayList<Integer>();
        File file = new File("input.txt");
        try(Scanner scanner = new Scanner(file)) {
            int i;
            while(scanner.hasNextInt()){
                i= scanner.nextInt();
                list.add(i);
            }
        } catch (IOException e) {
            System.out.println("File was not read");
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File was not read");
        }
        return list;
    }

    public void output(int counter){
        try(FileWriter writer = new FileWriter("output.txt", StandardCharsets.UTF_8, false))
        {
            writer.append(String.valueOf(counter));
            writer.flush();
        } catch (IOException e) {
            System.out.println("File is not written");
            throw new RuntimeException(e);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("File is not written");
        }
    }

    public int sortMethod(List list){
        n = (int) list.get(0);
        m = (int) list.get(1);
        list.remove(0);
        list.remove(0);
        if (n < 1 || n > 100000 || m < 1 || m > 100000){
            System.out.println("The file does not match the tack condition");
        } else {
            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < list.size(); i++) {
                int z = (int) list.get(i);
                if (set.size() < n && !set.contains(z)) {
                    set.add(z);
                    counter++;
                } else if (set.size() < n && set.contains(z)) {

                } else if (set.size() == n && set.contains(z)) {

                } else if (set.size() == n && !set.contains(z)) {
                    counter++;
                    Iterator<Integer> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        int x = iterator.next();
                        int sum = 0;
                        for (int j = list.indexOf(z); j < list.size(); j++) {
                            if (list.get(j).equals(x)) {
                                sum++;
                            }
                        }
                        if (sum == 0) {
                            set.remove(x);
                            set.add(z);
                            break;
                        }
                    }
                }
            }
        }
        return counter;
    }

    public static void main( String[] args )
    {
        long m = System.currentTimeMillis();
        long start = Runtime.getRuntime().freeMemory();
        App app = new App();
        List<Integer> list = app.input();
        if (!list.isEmpty()) {
            app.sortMethod(list);
            app.output(app.counter);
        } else {
            System.out.println("The list is empty");
        }
        long end = Runtime.getRuntime().freeMemory();
        long memoTaken = (start - end) / 1048576;
        long e = System.currentTimeMillis();
        long f = (e - m) / 1000;
        System.out.println("memory = " + memoTaken + "mB");
        System.out.println("time = " + f + "sec");
    }
}
