# Traffic police
Микросервис для дорожной полиции
## Stack
Данное приложение состоит из следующих технологий:
* Java
* Spring framework
  * Boot
  * Data JPA
  * MVC
    * Thymeleaf
* HTML
* CSS
* JavaScript
## Что это за приложение?
Это микросервис, который создан для помощи дорожной полиции.
Оно имеет:
* REST-сервис: принимает JSON-объекты и работает с ними в базе данных. Может использоваться к примеру, для автоматизации добавления правонарушений на автомобиль.
* Статичный MVC-клиент: используется в том случае, если нам нужно вручную добавить/редактировать какие-либо данные в базе.
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
