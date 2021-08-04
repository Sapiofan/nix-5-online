package services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CountPrimeNumbers implements Runnable { // via thread and getter

    private List<Integer> numbers;
    private List<Integer> primeNumbers;

    public CountPrimeNumbers(List<Integer> numbers){
        this.numbers = numbers;
    }

    public void run(){
        ArrayList<Integer> primeNums = new ArrayList<>();
        boolean flag = false;
        for (Integer number : numbers) {
            for (int i = 2; i < number; i++) {
                if(number % i == 0)
                    flag = true;
            }
            if(!(number == 0 || number == 1) && !flag){
                primeNums.add(number);
            }
            else
                flag = false;
        }
        setPrimeNumbers(primeNums);
    }

//    public List<Integer> call(){
//        ArrayList<Integer> primeNums = new ArrayList<>();
//        boolean flag = false;
//        for (Integer number : numbers) {
//            for (int i = 2; i < number; i++) {
//                if(number % i == 0)
//                    flag = true;
//            }
//            if(!(number == 0 || number == 1) && !flag){
//                primeNums.add(number);
//            }
//            else
//                flag = false;
//        }
//        return primeNums;
//    }


    public List<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

    public void setPrimeNumbers(List<Integer> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }
}
