# Основная документация по проекту

## Запуск проекта
1. Клонировать репозиторий
2. Создать копию файла .env.restore и переименовать в .env
3. Заполнить необходимые поля в .env, а именно
   1. Хост бд
   2. Порт бд
   3. Пользователь бд
   4. Пароль пользователя бд
   5. Имя бд
   6. Логин для почтового сервиса
   7. Токен для использования почтового сервиса
   8. Уровни логирования
4. Создать **локально** БД с помощью ddl-я (src/main/resources/scripts/create_all_tables.sql)
    - В DockerDesktop можно скачать Postgres, и задать параметры для сервера БД
5. Запустить проект (в intellij idea с помощью запуска ELibraryApplication.java)

## Swagger
Доступ к Swagger организован по ссылке `localhost:8080/` (реализована переадресация на сваггер)

- Выполнение некоторых функций swagger требует авторизации, необходимо будет создать пользователя в бд с нужной ролью. (пароль необходимо захешировать с помощью BCryptPasswordEncoder при прямом добавлении)
- Так же есть возможность создать пользователя с помощью админского метода (в данный момент он не требует прав администратора)


