package com.sketysoft.assignment;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas{

    public static void main(String[] args) {
        BasicLambdas.divider();
        new BasicLambdas().consumer();
        BasicLambdas.divider();
        new BasicLambdas().supplier();
        BasicLambdas.divider();
        new BasicLambdas().predicate();
        BasicLambdas.divider();
        new BasicLambdas().function();
        BasicLambdas.divider();
        new BasicLambdas().sortName(getPeople());
        BasicLambdas.divider();
        new BasicLambdas().sortAge(getPeople());
        BasicLambdas.divider();
        new BasicLambdas().sortHeight(getPeople());
        BasicLambdas.divider();
    }// end of main method

    private static void divider(){
        System.out.println("---------------------------------------------------------------");
    }

    public void consumer(){
        Printable<String> methodReference = System.out::println;
        methodReference.print("Custom Consumer Interface Printable Using MethodReference");

        Printable<String> lambda = l -> System.out.println(l);
        lambda.print("Custom Consumer Interface Printable Using Lambda");

        Consumer<String> builtInConsumer = System.out::println;
        builtInConsumer.accept("Java's Built In Consumer Interface Using MethodReference");

        Consumer<String> builtInConsumer2 = c2 -> System.out.println(c2);
        builtInConsumer2.accept("Java's Built In Consumer Interface Using Lambda");

    }// end of consumer method

    public void supplier(){

        Retrievable<Integer> customSupplier = () -> 10;
        System.out.println("Custom Supplier: Retrievable: " + customSupplier.retrieve());

        Supplier<Integer> builtInSupplier = () -> 10;
        System.out.println("Java's Built In Supplier: " +builtInSupplier.get());

    }// end of supplier

    public void predicate(){
        Evaluate<Integer> customPredicate = i -> i < 0;
        System.out.println("Custom Predicate: Evaluate: Is -" + 1 + " Negative Number? -> " + customPredicate.isNegative(-1));
        System.out.println("Custom Predicate: Evaluate: Is " + 1 + " Negative Number? -> " + customPredicate.isNegative(1));


        Predicate<Integer> builtInPredicate = i -> i < 0;
        System.out.println("Java's Built In Predicate: Is -" + 1 + " Negative Number? -> " + builtInPredicate.test(-1));
        System.out.println("Java's Built In Predicate: Is " + 1 + " Negative Number? -> " + builtInPredicate.test(1));

        int number = 4;
        System.out.println("Is " + number + " an even number : " + BasicLambdas.check(number, n -> n % 2 == 0));
        number = 7;
        System.out.println("Is " + number + " an even number : " + BasicLambdas.check(number, n -> n % 2 == 0));

        String name = "Mr. Joe Bloggs";
        System.out.println("Does " + name + " starts with: \"Mr.\" : " + BasicLambdas.check(name, n -> n.startsWith("Mr.")));
        name = "Ms. Ann Bloggs";
        System.out.println("Does " + name + " starts with: \"Ms.\" : " + BasicLambdas.check(name, n -> n.startsWith("Ms.")));
        Person person = new Person("Mike", 33, 1.8);
        System.out.println("Is " + person.getName() + " an Adult: " + BasicLambdas.check(person, p -> p.getAge() >= 18));
        person = new Person("Ann", 13, 1.4);
        System.out.println("Is " + person.getName() + " an Adult: " + BasicLambdas.check(person, p -> p.getAge() >= 18));

    }

    public static <T> boolean check(T t, Predicate<T> predicate){
        return predicate.test(t);
    }

    public void function(){
        Function<Integer,String> builtInFunction = i -> "Number is : " + i;
        System.out.println("Java's Built In Function: " + builtInFunction.apply(25));

        Functionable<Integer,String> customFunction = i -> "Number is : " + i;
        System.out.println("Custom Function: Functionable: " + customFunction.appleThis(25));
    }

    public void sortName(List<Person> list){
        list.sort(Comparator.comparing(Person::getName));
        System.out.println("After Sorting the List by Name!");
        list.forEach(System.out::println);
    }

    public void sortAge(List<Person> list){
        list.sort(Comparator.comparing(Person::getAge));
        System.out.println("After Sorting the List by Age!");
        list.forEach(System.out::println);
    }

    public  void sortHeight(List<Person> list){
        list.sort(Comparator.comparing(Person::getHeight));
        System.out.println("After Sorting the List by Height!");
        list.forEach(System.out::println);
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }

}
