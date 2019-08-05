package tech.namas.demo.tests.demo.services.impl;

import org.springframework.stereotype.Service;
import tech.namas.demo.tests.demo.services.BranchingService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchingServiceImpl implements BranchingService {

    @Override
    public String determineGender(String title) {
        switch (title) {
            case "Ms":
            case "Mrs":
            case "Miss":
                return "F";
            case "Mr": return "M";
            default:
                return "?";
        }
    }

    @Override
    public Boolean isEven(Integer value) {
        if (value % 2 == 0) {
            return true;
        }

        return false;
    }

    @Override
    public List<Integer> generateSequence(Integer count) {
        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            seq.add(i);
        }

        return seq;
    }

    @Override
    public List<String> fizzBuzz(Integer count) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(Integer.toString(i, 10));
            }
        }

        return result;
    }
}
