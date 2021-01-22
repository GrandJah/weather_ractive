package ru.job4j.weather;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

  private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();

  {
    weathers.put(1, new Weather(1, "Msc", 20));
    weathers.put(2, new Weather(2, "SPb", 15));
    weathers.put(3, new Weather(3, "Bryansk", 16));
    weathers.put(4, new Weather(4, "Smolensk", 21));
    weathers.put(5, new Weather(5, "Kiev", 25));
    weathers.put(6, new Weather(6, "Minsk", 18));
  }

  public Mono<Weather> findById(Integer id) {
    return Mono.justOrEmpty(weathers.get(id));
  }

  public Flux<Weather> all() {
    return Flux.fromIterable(weathers.values());
  }

  public Flux<Weather> findAllByTemperatureAfter(Integer temp) {
    return Flux.fromStream(weathers.values()
                                   .stream()
                                   .filter(weather -> weather.getTemperature() > temp));
  }

  public Mono<Weather> findTopByOrdereByTemperature() {
    return Mono.justOrEmpty(weathers.values()
                                    .stream()
                                    .max(Comparator.comparingInt(Weather::getTemperature)));
  }
}
