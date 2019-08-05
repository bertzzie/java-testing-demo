package tech.namas.demo.tests.demo.services;

import java.util.List;

public interface BranchingService {

    String determineGender(String title);

    Boolean isEven(Integer value);

    List<Integer> generateSequence(Integer count);

    List<String> fizzBuzz(Integer count);
}
