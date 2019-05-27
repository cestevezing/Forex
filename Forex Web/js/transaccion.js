var total = 0
var inver 

$(document).ready(function () {
 
    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/usuario/inversion',
        type: 'POST',
        contentType: "application/json",
        headers: {
            'token-auto': document.cookie
        },
        success: function (data, textstatus, jQxhr) {
            inver = data.respuesta
            document.getElementById("inversion").value = inver
        },
        error: function (data, textstatus, jQxhr) {
            alert("Error")
        }

    })



    var refreshId = setInterval(function () {
        $('#feedback-bg-info').load(
            total = 0,
        
                $.ajax({
                url: 'http://localhost:8080/Forex-web/api/transaccion/actualizar',
                type: 'POST',
                contentType: "application/json",
                headers: {
                    'token-auto': document.cookie
                },
                success: function (data, textstatus, jQxhr) {
                    var contenido = document.querySelector('#rest')
                    contenido.innerHTML = ''
                    for (let valor of data) {
                        contenido.innerHTML += `
                        
                            <tr>
                                <th scope="row">${ valor.id}</th>
                                <td>${ valor.divisaId = 1 ? "EUR/USD" : "GBP/USD"}</td>
                                <td>${ valor.base.toFixed(5)}</td>
                                <td>${ valor.actual.toFixed(5)}</td>
                                <td>${ valor.valuePip}</td>
                                <td>${ gana(valor.actual, valor.base, valor.valuePip).toFixed(2)}</td>
                                <td><button type="button" class="btn btn-outline-light" onclick="vender(${valor.id})">Vender</button></td>
                            </tr>
                            
                            `
                    }
                    var suma = parseFloat(inver) + parseFloat(total)
                    document.getElementById("ganancias").value = suma.toFixed(2);
                    document.getElementById("mensaje").textContent = ""

                },
                error: function (data, textstatus, jQxhr) {
                    document.getElementById("mensaje").textContent = data.responseJSON.respuesta
                }

            })
        );
    }, 3000);

})

function gana(actual, base, pip) {

    total = total + ((actual - base) * pip)*100000
    return ((actual - base) * pip)*100000
}

function vender(id) {
    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/prueba/registro',
        type: 'POST',
        contentType: "application/json",
        headers: {
            'token-auto': document.cookie
        },
        success: function (data, textstatus, jQxhr) {

        },
        error: function (data, textstatus, jQxhr) {
            alert(data.responseJSON.respuesta)
        }

    })
}



function comprar() {
    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/transaccion/comprar',
        type: 'POST',
        contentType: "application/json",
        headers: {
            'token-auto': document.cookie
        },
        data: JSON.stringify({
            id: 0,
            userId: 0,
            divisaId: document.getElementById("divisa").value,
            base: 0,
            actual: 0,
            state: false,
            valuePip: document.getElementById("valor").value

        }),
        success: function (data, textstatus, jQxhr) {
            alert(data.respuesta)
        },
        error: function (data, textstatus, jQxhr) {
            alert(data.responseJSON.respuesta)
        }

    })
}

