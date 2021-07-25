package main;

import services.FindNearestLesson;

public class Main {
    public static void main(String[] args) {
        FindNearestLesson lesson = new FindNearestLesson();
        lesson.findingById(1);
    }
}
