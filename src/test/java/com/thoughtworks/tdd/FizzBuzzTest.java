package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

    //数字包含3只打印Fizz
    @Test
    public void shoul_return_1_when_input_1() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(1);
        assert (result.equals("1"));
    }

    @Test
    public void should_return_fizz_when_input_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(3);
        assert (result.equals("Fizz"));
    }

    @Test
    public void should_return_Buzz_when_input_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(5);
        assert (result.equals("Buzz"));
    }

    @Test
    public void should_return_Whizz_when_input_7() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(7);
        assert (result.equals("Whizz"));
    }

    @Test
    public void should_return_FizzWhizz_when_input_21() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(21);
        assert (result.equals("FizzWhizz"));
    }

    @Test
    public void should_return_Fizz_when_input_endOf_3() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(13);
        assert (result.equals("Fizz"));
    }

    @Test
    public void should_return_Buzz_when_input_endOf_5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(15);
        assert (result.equals("Buzz"));
    }

    @Test
    public void should_return_Buzz_when_input_endOf_7() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String result = fizzBuzz.fizzBuzz(17);
        assert (result.equals("Whizz"));
    }
}
