-- Добавление аккаунтов для авторизации
INSERT INTO auth_info.roles(name)
  VALUES('ROOT'),('EDITOR');
INSERT INTO auth_info.users(name, password, enabled)
  VALUES('root', 'root123', true),
    ('editor', 'editor123', true);
INSERT INTO auth_info.user_roles(user_id, role_id)
  VALUES(1, 1),(2,2);

-- Добавление участников
INSERT INTO persons_info.addresses(city, street, house, flat)
  VALUES('Москва', 'Пушкина', '9', '1');
INSERT INTO persons_info.contacts(phone_number, email)
  VALUES('72334903710', null);
INSERT INTO persons_info.persons(address_id, contact_id, first_name,
  last_name, patronymic, birth_date)
  VALUES(1, 1, 'Алексей', 'Свислотцкий', 'Олегович', '1995-03-15');

INSERT INTO persons_info.addresses(city, street, house, flat)
  VALUES('Москва', 'Пушкина', '8', '34');
INSERT INTO persons_info.contacts(phone_number, email)
  VALUES('79939903610', null);
INSERT INTO persons_info.persons(address_id, contact_id, first_name,
  last_name, patronymic, birth_date)
  VALUES(2, 2, 'Виктор', 'Краснов', 'Викторович', '1995-03-25');

-- Автомобили
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(1, 'BMW', 'X6', 'чёрный', 'Д233ХЗ', '2003-07-12');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(null, 'Audi', 'A8', 'красный', 'Й827ЛЗ', '2000-12-10');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(null, 'Audi', 'A5', 'зелёный', 'А123КП', '2008-07-14');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(1, 'Renault', 'Logan', 'синий', 'А999ЕН', '2012-09-28');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(null, 'Mazda', '6', 'крансый', 'Ы765БЕ', '2014-05-05');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(null, 'Mazda', 'CX-30', 'чёрный', 'М843ЗЩ', '2018-03-10');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(2, 'Mitsubishi', 'Eclipse Cross', 'серебрянный', 'К123ЦЗ', '2020-07-10');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(null, 'Renault', 'Logan', 'красный', 'Е217НК', '2015-03-17');
INSERT INTO cars_info.cars(owner_id, brand, model, color, register_number, created)
  VALUES(null, 'Toyota', 'Supra', 'фиолетовый', 'З009ЗЦ', '2007-08-12');


-- Добавление штрафов
INSERT INTO cars_info.violations(car_id, violator_id, message, active, time)
  VALUES(1, 1, 'Проезд на красный свет', false, '2013-08-12 13:15:03');
INSERT INTO cars_info.violations(car_id, violator_id, message, active, time)
  VALUES(1, 1, 'Обгон по встречной полосе', true, '2015-03-03 17:23:00');
