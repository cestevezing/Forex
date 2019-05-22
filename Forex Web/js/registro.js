$('#formulario').submit(function (evento) {
    evento.preventDefault();
    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/prueba/registro',
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            name: document.getElementById("name").value,
            id: document.getElementById("id").value,
            nameUser : document.getElementById("userName").value,
            email : document.getElementById("email").value,
            password : document.getElementById("password").value,
            outlay : 0,
            earnings : 0
        }),
        success: function (data, textstatus, jQxhr) {
            alert(data);
            window.location = "Login.html";
        },
        error: function (data, textstatus, jQxhr) {
            alert('El UserName o Id ya existen')
        }
    })
});

$.getJSON("https://api.ipify.org/?format=json", function(e) { console.log(e.ip); }); 