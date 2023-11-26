### (RU)
## Задача
Имеется некая система, которая обрабатывает авиаперелёты.

Перелёт — это перевозка пассажира из одной точки в другую с возможными промежуточными посадками.
Это значит, что перелёт можно представить как набор из одного или нескольких элементарных перемещений, называемых сегментами.

Сегмент — это атомарная перевозка, которую для простоты будем характеризовать всего двумя атрибутами: дата/время вылета и дата/время прилёта.

Ваша задача — написать модуль, который будет заниматься фильтрацией набора перелётов согласно различным правилам.
Правил фильтрации может быть очень много.
Наборы перелётов также могут быть очень большими.
Правила могут выбираться и задаваться динамически в зависимости от контекста выполнения операции фильтрации.

## Что нужно сделать?
Продумайте структуру модуля, создайте необходимые классы и интерфейсы.
Если знакомы с JUnit - покройте свой код тестами.
Пользовательский интерфейс не рассматривайте.
Достаточно вывода информации в консоль.
Никаких сторонних библиотек использовать не нужно.

## Что нужно учесть?
Приложенный файл TestClasses.java содержит упрощённые модельные классы и фабрику для получения тестовых образцов.
Весь код необходимо поместить в пакет com.gridnine.testing.
Для проверочного запуска создайте публичный класс Main c методом main().
Этот метод должен выдать в консоль результаты обработки тестового набора перелётов.
Получить тестовый набор нужно методом FlightBuilder.createFlights().

Исключите из тестового набора перелёты по следующим правилам (по каждому правилу нужен отдельный вывод списка перелётов):
1. Вылет до текущего момента времени.
2. Сегменты с датой прилёта раньше даты вылета.
3. Перелеты, где общее время, проведённое на земле, превышает два часа
   (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним).

## Запуск приложения
- Запустите метод main в классе Main.class

### (EN)
## Task
There is a certain system that handles air flights.

A flight is the transportation of a passenger from one point to another with possible intermediate landings.
This means that a flight can be represented as a set of one or more elementary movements, called segments.

A segment is an atomic transportation, which for simplicity we will characterize with only two attributes: date/time of departure and date/time of arrival.

Your task is to write a module that will filter a set of flights according to various rules.
There can be a lot of filtering rules.
Sets of flights can also be very large.
Rules can be selected and set dynamically depending on the context of the filtering operation.

## What needs to be done?
Think over the structure of the module, create the necessary classes and interfaces.
If you are familiar with JUnit, cover your code with tests.
Don't consider the user interface.
It is enough to output information to the console.
You don't need to use any third-party libraries.

## What should be taken into account?
Attached testClasses file.java contains simplified model classes and a factory for obtaining test samples.
All the code must be placed in the com.package.gridnine.testing.
For a test run, create a public Main class with the main() method.
This method should output the results of processing the test set of flights to the console.
You need to get the test set using the Flight Builder.createFlights() method.

Exclude flights according to the following rules from the test set (a separate list of flights is needed for each rule):
1. Departure before the current time.
2. Segments with arrival date before departure date.
3. Flights where the total time spent on the ground exceeds two hours
   (time on earth is the interval between the arrival of one segment and the departure of the next one).

## How to run the application
- Run the main method in the Main.class.