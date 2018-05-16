package aop;

import model.Event;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CounterAspect {

    private Map<Event, Integer> counter = new HashMap<>();

    @Pointcut("@annotation(aop.Counter)")
    public void firstPointcut() {
    }

    @AfterReturning(
            pointcut = "firstPointcut()",
            returning = "event")
    public void runAspect(JoinPoint jp, Event event) {
        if (!counter.containsKey(event)) {
            counter.put(event, 1);
        } else counter.put(event, counter.get(event) + 1);

    }


    @AfterReturning("execution(* *.makeTheChoice(..))")
    public void showStats () {
        counter.entrySet().forEach(key -> System.out.println(key.getKey().getName() + ": " + key.getValue() + "ticket(s) viewed"));
    }

}
