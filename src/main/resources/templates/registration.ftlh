<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация - BitMarket</title>
    <link rel="stylesheet" href="css/login-register.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
</head>
<body class="body-form">
<#include "schemes/homebtn.ftl"/>
<div class="form">
    <div class="inner-form-data">
        <div class="form-title">
            <h3>Регистрация</h3> <small style="color: gray">или <a href="/login">войти</a>?</small>
        </div>
        <form method="post" id="page-form" action="/register">
            <div class="form-body">
                <div class="body-line">
                    <label class="input-name">
                        Email
                    </label>
                    <input id="email-field" class="input-area" required type="email" name="email">
                </div>
                <div id="check-feedback"></div>
                <div class="body-line">
                    <label class="input-name">
                        Пароль
                    </label>
                    <input class="input-area pass" minlength="8" maxlength="32" required type="password" name="password" id="pass">
                    <div class="eye-container">
                        <span class="show-pass-icon" id="eye" title="Показать пароль"></span>
                    </div>
                </div>
                <small id="pass-help" class="hidden" style="color: black">Длина пароля должна быть в пределах 8 - 32 символов</small>
                <div class="body-line">
                    <label class="input-name">
                        Повторите пароль
                    </label>
                    <input class="input-area" required type="password" id="retryPass">
                </div>
                <div class="body-line">
                    <label class="input-name">
                        Фамилия
                    </label>
                    <input class="input-area" required type="text" name="secondName">
                </div>
                <div class="body-line">
                    <label class="input-name">
                        Имя
                    </label>
                    <input class="input-area" required type="text" name="firstName">
                </div>
                <div class="body-line">
                    <label class="input-name">
                        Отчество
                    </label>
                    <input class="input-area" type="text" name="thirdName">
                </div>
            </div>
            <div class="form-bottom">
                <a href="#conf" onclick="confirm()">Подтвердить</a>
            </div>
        </form>
    </div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
    const showPassButton = document.getElementById("eye");
    showPassButton.addEventListener('click', () => {
        const inputPass = document.getElementById("pass");
        if (inputPass.getAttribute('type') === 'password'){
            inputPass.setAttribute('type', 'text');
            showPassButton.setAttribute('title', "Скрыть пароль");
            showPassButton.classList.add('eye-active');
        } else{
            inputPass.setAttribute('type', 'password');
            showPassButton.setAttribute('title', "Показать пароль");
            showPassButton.classList.remove('eye-active');
        }
    })
    function confirm(){
        let pass = document.getElementById("pass").value;
        let retryPass = document.getElementById("retryPass").value;
        if(pass !== retryPass){
            alert("Пароли не совпадают!");
        }
        else{
            var email = $("#email-field").val();
            $.get("/check-email", { email: email })
                .done(function (response) {
                    if (response.available) {
                        $("#check-feedback").html('<small style="color: green">Email доступен</small>');
                        if(document.getElementById("page-form").checkValidity()){
                            document.getElementById("page-form").submit();
                        }
                        if(pass.size < 8 || pass.size > 32){
                            alert("Пароль не соответствует требованиям безопасности!")
                        }
                    } else {
                        $("#check-feedback").html('<small style="color: red;">Данный email уже зарегистрирован</small>');
                    }
                })
                .fail(function() {
                    $("#check-feedback").html('<small style="color: red;">Произошла ошибка. Попробуйте позже.</small>');
                });
        }
    }
</script>
</body>
</html>
