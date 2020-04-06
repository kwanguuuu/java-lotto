package lotto.domain;

import lotto.exception.NotANumberException;
import lotto.exception.NumberDuplicateException;
import lotto.exception.OutOfRangeException;

import java.util.*;
import java.util.stream.Collectors;

public class Numbers {
    public static final int TICKET_MIN_NUMBER = 1;
    public static final int TICKET_MAX_NUMBER = 45;
    public static final int NUMBER_LIMIT_COUNT = 6;

    private final Set<Integer> numbers;
    private int bonusNumber;

    public Numbers(List<Integer> inputNumber) {
        checkLengthValidation(inputNumber);
        this.numbers = new HashSet<>(inputNumber);
        checkNumberValidation(numbers);
        checkDuplicate(numbers);
    }

    public boolean hasBonusNumber(){
        return numbers.contains(this.bonusNumber);
    }

    public Set<Integer> showNumbers() {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void checkLengthValidation(List<Integer> numberInformation) {
        if (numberInformation.size() != NUMBER_LIMIT_COUNT) {
            throw new OutOfRangeException();
        }
    }

    private Set<Integer> checkNumberValidation(Set<Integer> numbers) {
        for (int number : numbers) {
            checkNumberRange(number);
        }
        return numbers;
    }

    private void checkDuplicate(Set<Integer> inputNumber) {
        if (inputNumber.size() < NUMBER_LIMIT_COUNT) {
            throw new NumberDuplicateException();
        }
    }

    private void checkNumberRange(int number) {
        if (number < TICKET_MIN_NUMBER || number > TICKET_MAX_NUMBER) {
            throw new NotANumberException(number);
        }
    }

    public void addBonusNumber(int bonusNumber) {
        checkNumberRange(bonusNumber);
        bonusNumberValidator(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void bonusNumberValidator(int bonusNumber) {
        if(numbers.contains(bonusNumber)){
            throw new RuntimeException("중복된 로또 번호");
        }
    }
}
