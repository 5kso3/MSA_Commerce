package com.example.myrestfulservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
// GET
// /hello-world(endpoint)
// @RequestMapping (method= RequsetMethod.GET, path = "/hello-world")

public class HelloWorldController {

    @GetMapping(path="/hello-world")
    public String helloWorld() {
        return ("Hello World");
    }

    //hello-world-bean/path-variable/
    @GetMapping(path="/hello-world-bean/path-variable/{name}/{major}")
    public helloWorldBean helloWorldBean(@PathVariable String name, @PathVariable String major) {
        return new helloWorldBean (String.format("Hello World, %s, %s", name, major));
    }

}
