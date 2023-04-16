package com.applyandgrowth.util;

import java.util.ArrayList;
import java.util.List;

public class DietFactory {
    public static class Meal {
        private String name;
        private String grams;
        private String weekDay;
        private String time;

        public Meal(String name, String grams, String weekDay, String time) {
            this.setName(name);
            this.setGrams(grams);
            this.setWeekDay(weekDay);
            this.setTime(time);
        }

        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public String getWeekDay() {
            return weekDay;
        }
        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getGrams() {
            return grams;
        }
        public void setGrams(String grams) {
            this.grams = grams;
        }
    }

    public static List<Meal> makeDiet1() {
        List<Meal> meals = new ArrayList<Meal>();

        meals.add(new Meal("item1", "500", "MON", "12:00"));
        meals.add(new Meal("item1", "500", "MON", "20:00"));

        meals.add(new Meal("item1", "500", "TUE", "12:00"));
        meals.add(new Meal("item1", "500", "TUE", "20:00"));

        meals.add(new Meal("item1", "500", "WED", "12:00"));
        meals.add(new Meal("item1", "500", "WED", "20:00"));

        meals.add(new Meal("item1", "500", "THU", "12:00"));
        meals.add(new Meal("item1", "500", "THU", "20:00"));

        meals.add(new Meal("item1", "500", "FRI", "12:00"));
        meals.add(new Meal("item1", "500", "FRI", "20:00"));
        
        meals.add(new Meal("item1", "500", "SAT", "12:00"));
        meals.add(new Meal("item1", "500", "SAT", "20:00"));

        meals.add(new Meal("item1", "500", "SUN", "12:00"));
        meals.add(new Meal("item1", "500", "SUN", "20:00"));

        return meals;
    }

    public static List<Meal> makeDiet2() {
        List<Meal> meals = new ArrayList<Meal>();

        meals.add(new Meal("item2", "500", "MON", "12:00"));
        meals.add(new Meal("item2", "500", "MON", "20:00"));

        meals.add(new Meal("item2", "500", "TUE", "12:00"));
        meals.add(new Meal("item2", "500", "TUE", "20:00"));

        meals.add(new Meal("item2", "500", "WED", "12:00"));
        meals.add(new Meal("item2", "500", "WED", "20:00"));

        meals.add(new Meal("item2", "500", "THU", "12:00"));
        meals.add(new Meal("item2", "500", "THU", "20:00"));

        meals.add(new Meal("item2", "500", "FRI", "12:00"));
        meals.add(new Meal("item2", "500", "FRI", "20:00"));
        
        meals.add(new Meal("item2", "500", "SAT", "12:00"));
        meals.add(new Meal("item2", "500", "SAT", "20:00"));

        meals.add(new Meal("item2", "500", "SUN", "12:00"));
        meals.add(new Meal("item2", "500", "SUN", "20:00"));

        return meals;
    }

    public static List<Meal> makeDiet3() {
        List<Meal> meals = new ArrayList<Meal>();

        meals.add(new Meal("item3", "500", "MON", "12:00"));
        meals.add(new Meal("item3", "500", "MON", "20:00"));

        meals.add(new Meal("item3", "500", "TUE", "12:00"));
        meals.add(new Meal("item3", "500", "TUE", "20:00"));

        meals.add(new Meal("item3", "500", "WED", "12:00"));
        meals.add(new Meal("item3", "500", "WED", "20:00"));

        meals.add(new Meal("item3", "500", "THU", "12:00"));
        meals.add(new Meal("item3", "500", "THU", "20:00"));

        meals.add(new Meal("item3", "500", "FRI", "12:00"));
        meals.add(new Meal("item3", "500", "FRI", "20:00"));
        
        meals.add(new Meal("item3", "500", "SAT", "12:00"));
        meals.add(new Meal("item3", "500", "SAT", "20:00"));

        meals.add(new Meal("item3", "500", "SUN", "12:00"));
        meals.add(new Meal("item3", "500", "SUN", "20:00"));

        return meals;
    }
}
