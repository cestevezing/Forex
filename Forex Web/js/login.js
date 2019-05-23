
$('#fomulario').submit(function(evento) {
    evento.preventDefault();
    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/usuario/login/'+document.getElementById("username").value +'/'+document.getElementById("pass").value,
        type: 'GET',
        contentType: "application/json",
        success:function(data,textstatus,jQxhr){
            alert(data.respuesta),
            window.location = "u_home.html"
        },
        error: function(data){
            alert(data.responseJSON.respuesta)
        } 
        
    })
});
   

