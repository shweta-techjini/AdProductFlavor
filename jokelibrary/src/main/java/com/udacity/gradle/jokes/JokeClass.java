package com.udacity.gradle.jokes;

import java.util.Random;

public class JokeClass {

    public static String[] jokes = new String[]{
            "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.",
            "Anton, do you think I’m a bad mother? My name is Paul.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "My wife’s cooking is so bad we usually pray after our food.",
            "Why is it a bad idea for two butt cheeks to get married? Because they part for every little shit.",
            "I'd like to buy a new boomerang please. Also, can you tell me how to throw the old one away?",
            "It is so cold outside I saw a politician with his hands in his own pockets.",
            "There is nothing worse than child polio. No wait, there's women's soccer.",
            "I wanted to grow my own food but I couldn’t get bacon seeds anywhere.",
            "I can’t believe I forgot to go to the gym today. That’s 7 years in a row now.",
            "Woke up with a dead leg this morning. I will not take out a loan with the mafia ever again.",
            "The 21st century: Deleting history is often more important than making it.",
            "How do you tell that a crab is drunk? It walks forwards.",
            "Why do cows wear bells? Their horns don’t work.",
            "I’m selling my talking parrot. Why? Because yesterday, the bastard tried to sell me."
    };

    public static String getJoke() {
        Random random = new Random();
        int position = random.nextInt(jokes.length);
        return jokes[position];
    }
}
