package com.naya.exams.metric;

import com.naya.exams.services.MathService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Aleksandr Barmin
 */
@Configuration
public class MetricsConfiguration {
  @Bean
  public MeterBinder availableExercisesMetric(MathService mathService) {
    return registry ->
        Gauge.builder("exercise.max.available", mathService::getMaxAvailableNumber)
            .baseUnit("NUMBER")
            .description("Maximal value available in the exercise")
            .register(registry);
  }
}
