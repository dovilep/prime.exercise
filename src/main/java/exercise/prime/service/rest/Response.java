package exercise.prime.service.rest;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Response {
    private final int initial;
    private final List<Integer> primes;
}
