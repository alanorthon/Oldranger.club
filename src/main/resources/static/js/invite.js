function test() {

    fetch('http://localhost:8888/api/token/invite', {method: 'POST'})
        .then(response => response.json())
        .then(key => {
            let p = document.getElementById("innerFieldText");
            p.innerText = "http://localhost:8888/invite?key=" + key;
        });
}

function sendDataUser() {
    fetch('http://localhost:8888/api/users', {
        method: 'POST',
        body: $_GET('key')
    });
}

function $_GET(key) {
    let p = window.location.search;
    p = p.match(new RegExp(key + '=([^&=]+)'));
    return p ? p[1] : false;
}

function sendInviteOnMail() {
    let field = document.getElementById("mail");
    let mail = field.value;
    if (mail != 0) {
        if (validateEmail(mail)) {
            fetch('http://localhost:8888/api/token/invite/bymail', {method: 'POST', body: mail})
                .then(response => response.json())
                .then(status => {
                    if (status == '1') {
                        alert("Приглашение успешно отправлено на почту: " + mail);
                    } else {
                        alert("Приглашение не может быть доставлено по адресу:" + mail + "!");
                    }
                });
        } else {
            alert("Адрес отправки указан неверно!");
        }

        field.value = "";

    } else {
        alert("Адрес отправки не указан!");
    }
}

function validateEmail(email) {
    let pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(String(email).toLowerCase());
}