package com.thoughtworks.tdd;

public class FizzBuzz {
    public String fizzBuzz(int i) {
        StringBuffer sb = new StringBuffer();
        if (i % 3 == 0 )
            sb.append("Fizz");
        if (i % 5 == 0) {
            sb.append("Buzz");
        }
        if (i % 7 == 0) {
            sb.append("Whizz");
        }
        if (i % 10 == 3 ){
            sb.delete(0, sb.length());
            sb.append("Fizz");
        }
        if (i % 10 == 5) {
            sb.delete(0, sb.length());
            sb.append("Buzz");
        }
//        if (i % 10 == 7) {
//            sb.append("Whizz");
//        }


        return sb.toString().equals("") ? String.valueOf(i) : sb.toString();
    }
}
