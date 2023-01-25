# Traffic police
Микросервис для дорожной полиции
## Технологический стек приложения
Данное приложение состоит из следующих технологий:
* Java
* Spring framework
  * Boot
  * Data JPA
  * MVC
    * Thymeleaf
* HTML, CSS, JavaScript - для MVC
* PostgreSQL - база данных, для хранения информации
## Что это за приложение?
Это микросервис, который создан для помощи дорожной полиции.
Оно имеет:
* REST-сервис: принимает JSON-объекты и работает с ними в базе данных. Может использоваться к примеру, для автоматизации добавления правонарушений на автомобиль. К примеру, камера на дороге, которая фиксирует нарушения, может обращаться к приложению через REST (например, добавить штраф к зафиксированному номеру автомобиля, который привысел скорость).
* Статичный MVC-клиент: используется в том случае, если нам нужно вручную добавить/редактировать какие-либо данные в базе.
* Авторизация на основе ролей:
  * EDITOR (или же - редактор): имеет право добавлять участников, автомобили и штрафы, а также их редактировать и удалять;
  * ROOT (или же - админ): имеет такие же привелегии, как и EDITOR, но также может добавлять новых пользователей в программу;
### Интерфейс приложения
* Страница авторизации: \
![auth](https://github.com/Jankbyte/traffic-police/blob/main/github-res/images/auth.png)
* Панель управления для редакторов/админов: \
![auth](https://github.com/Jankbyte/traffic-police/blob/main/github-res/images/panel.png)
* Список пользователей: \
![auth](https://github.com/Jankbyte/traffic-police/blob/main/github-res/images/users.png)
* Список автомобилей: \
![auth](https://github.com/Jankbyte/traffic-police/blob/main/github-res/images/cars.png)
* У каждого автомобиля можно посмотреть штрафы: \
![auth](https://github.com/Jankbyte/traffic-police/blob/main/github-res/images/violations.png)
* Список участников: \
![auth](https://github.com/Jankbyte/traffic-police/blob/main/github-res/images/persons.png)
## Конфигурация и запуск
Для запуска приложения потребуется база данных PostgreSQL (или же альтернативы, но тогда потребуется сменить JDBC-драйвер), настроить базу под себя - можно в [этом] файле:

[этом]: https://github.com/Jankbyte/traffic-police/blob/main/src/main/resources/application.yml

