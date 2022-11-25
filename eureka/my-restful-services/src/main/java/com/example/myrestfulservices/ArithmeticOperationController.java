package com.example.myrestfulservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArithmeticOperationController {

    @GetMapping(path="/plus/{A}/{B}")
    public ArithmeticOperationBean plus(@PathVariable int A, @PathVariable int B) {
        return new ArithmeticOperationBean (A+B, "입니다.");
    }

    @GetMapping(path="/minus/{A}/{B}")
    public ArithmeticOperationBean minus(@PathVariable int A, @PathVariable int B) {
        return new ArithmeticOperationBean (A-B, "입니다.");
    }

    @GetMapping(path="/multiply/{A}/{B}")
    public ArithmeticOperationBean multiply(@PathVariable int A, @PathVariable int B) {
        return new ArithmeticOperationBean (A*B, "입니다.");
    }

    @GetMapping(path="/divide/{A}/{B}")
    public ArithmeticOperationBean divide(@PathVariable int A, @PathVariable int B) {
        return new ArithmeticOperationBean (A/B, "입니다.");
    }
}
