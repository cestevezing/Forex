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
        document.getElementById("userName").value = data.userName;
        document.getElementById("userName").disabled = true;
        document.getElementById("password").value = data.password;
    },
    error: function (data) {
        alert(data.responseJSON.respuesta)
    }
    
});
