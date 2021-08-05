package services;

import java.util.ArrayList;
import java.util.List;

public class CountPrimeNumbers implements Runnable {

    private List<Integer> numbers;
    private List<Integer> primeNumbers;

    public CountPrimeNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        ArrayList<Integer> primeNums = new ArrayList<>();
        boolean flag = false;
        for (Integer number : numbers) {
            for (int i = 2; i < number; i++) {
                if (number % i == 0)
                    flag = true;
            }
            if (!(number == 0 || number == 1) && !flag) {
                primeNums.add(number);
            } else
                flag = false;
        }
        this.primeNumbers = primeNums;
    }


    public List<Integer> getPrimeNumbers() {
        return primeNumbers;
    }
}
