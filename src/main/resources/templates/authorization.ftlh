<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Авторизация - BitMarket</title>
    <link rel="stylesheet" href="css/login-register.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
</head>
<body class="body-form">
<#include "schemes/homebtn.ftl"/>
<div class="form">
    <div class="inner-form-data">
        <div class="form-title">
            <h3>Вход</h3> <small style="color: gray">или <a href="/register">зарегистрироваться</a>?</small>
        </div>
        <form method="post" id="page-form" action="/login">
            <div class="form-body">
                <div class="body-line">
                    <label class="input-name">
                        Email
                    </label>
                    <input id="email-field" class="input-area" required type="email" name="username">
                </div>
                <div class="body-line">
                    <label class="input-name">
                        Пароль
                    </label>
                    <input class="input-area" required type="password" name="password" id="pass" style="width:65%">
                    <div class="eye-container">
                        <span class="show-pass-icon" id="eye" title="Показать пароль"></span>
                    </div>
                </div>
            </div>
            <div class="form-bottom">
                <a href="#conf" onclick="submit()">Войти</a>
            </div>
        </form>
        <small style="color: gray"><a href="/pass-recovery">Забыли пароль?</a></small>
    </div>
</div>
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
    function submit(){
        document.getElementById("page-form").submit();
    }
</script>
</body>
</html>
