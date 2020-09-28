package com.company;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.util.*;

/*
Наступила осень и в нашем городе похолодало.
Вы находитесь в аэропорту Иркутска и решаете, куда можно полететь.
Используйте сервис OpenWeatherMap, чтобы выбрать город с наиболее тёплой погодой.
Вам необходимо поиском по OpenWeatherMap собрать ID как минимум 10 городов.
Например, для Шелехова это 2016764.
Ваша программа должна вывести города и температуру в них в порядке убывания температуры.
 список городов считать из файла или запросить у пользователя
В качестве ответа приложите исходный год класса(-ов) и текстовый файл с примером работы программы.
 */
class Main_w {
    public  float  temp;
}
class Weather {
    Main_w main;
    String name; // id of the city
}

class Compare implements Comparator<Weather> {
    @Override
    public int compare(Weather c1, Weather c2){
        return Double.compare(c1.main.temp, c2.main.temp);
    }
}

public class Main {

    public static Weather getTempByCity(String name) {
        Gson gson = new Gson();
        String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=b20838b2394c8d57577c818c443cb10c&units=metric";
        Weather weather = new Weather();
        try {
            URL url = new URL(API_URL);
            InputStream stream = (InputStream) url.getContent();
            // передаём сетевой поток специальному считывателю
            InputStreamReader reader = new InputStreamReader(stream);
            weather = gson.fromJson(reader, Weather.class);
        } catch (IOException e) {
            System.out.println(e.getMessage()); // в случае исключения выдаём ошибку на экран
        }
        return weather; // вернуть значение температуры из объекта weather
    }


    public static void main(String[] args) throws IOException {
        // jar https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar

        // список городов считать из файла или запросить у пользователя
        Scanner scanner = new Scanner(new File("cities.txt"));

        ArrayList<Weather> cities = new ArrayList<Weather>();

        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            Weather weather = new Weather();
            weather = getTempByCity(name);
            // System.out.println(weather.name + " " + weather.main.temp);
            cities.add(weather);
        } scanner.close();

        Collections.sort(cities, new Compare());
        Collections.reverse(cities);

        for (Weather c : cities){
            System.out.println(c.name+" "+c.main.temp);
        }
    }
//1) Создать классы так сделано
//2) Создать ArrayList<НАЗВАНИЕКЛАССА>
//3) Заполнить ArrayList
//4) сделать .sort()
//5) ....???
//6) PROFIT

}