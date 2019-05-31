$.ajax({
    url: 'http://localhost:8080/Forex-web/api/usuario/perfil',
    type: 'POST',
    contentType: "application/json",
    headers:{
        'token-auto': document.cookie
    },
    success: function (data, textstatus, jQxhr) {
        document.getElementById("id").value = data.id;
        document.getElementById("id").disabled = true;
        document.getElementById("name").value = data.name;
        document.getElementById("email").value = data.email;
        document.getElementById("userName").value = data.nameUser;
        document.getElementById("userName").disabled = true;
        document.getElementById("password").value = data.password;
        document.getElementById("outlay").value = data.outlay;
    },
    error: function (data) {
        alert(data.responseJSON.respuesta)
    }
    
});


$('#formulario').submit(function (evento) {
    evento.preventDefault();
    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/usuario/save',
        type: 'POST',
        contentType: "application/json",
        headers:{
            'token-auto': document.cookie
        },
        data: JSON.stringify({
            name: document.getElementById("name").value,
            id: document.getElementById("id").value,
            nameUser : document.getElementById("userName").value,
            email : document.getElementById("email").value,
            password : document.getElementById("password").value,
            outlay : document.getElementById("outlay").value,
            earnings : 0
        }),
        success: function (data, textstatus, jQxhr) {
            alert(data.respuesta);
            window.location = "u_home.html";
        },
        error: function (data, textstatus, jQxhr) {
            alert(data.responseJSON.respuesta)
        }
    })
});
