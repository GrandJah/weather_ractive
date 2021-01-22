package ru.job4j.weather;

import lombok.Getter;

@Getter
public class Weather {
  private final int id;

  private final String city;

  private final int temperature;

  public Weather(int id, String city, int temperature) {
    this.id = id;
    this.city = city;
    this.temperature = temperature;
  }
}
