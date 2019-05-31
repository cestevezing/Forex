
$(document).ready(function () {

    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/transaccion/historial',
        type: 'GET',
        contentType: "application/json",
        headers: {
            'token-auto': document.cookie
        },
        success: function (data, textstatus, jQxhr) {
            console.log(data)
            
            new Morris.Line({
                // ID of the element in which to draw the chart.
                element: 'myfirstchart',
                // Chart data records -- each entry in this array corresponds to a point on
                // the chart.
                data,
                // The name of the data record attribute that contains x-values.
                xkey: ['userId'],
                // A list of names of data record attributes that contain y-values.
                ykeys: ['actual'],
                labels: ['Ganacias'],
                resize: true,
                pointSize: 0,
                lineColors: ['#FFFFFF']
            })
        },
        error: function (data) {
            alert(data.responseJSON.respuesta)
        }

    })


    $.ajax({
        url: 'http://localhost:8080/Forex-web/api/transaccion/realizadas',
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
                        <td>${ valor.divisaId == 1 ? "EUR/USD" : "GBP/USD"}</td>
                        <td>${ valor.base.toFixed(5)}</td>
                        <td>${ valor.actual.toFixed(5)}</td>
                        <td>${ valor.valuePip}</td>
                        <td>${ gana(valor.actual, valor.base, valor.valuePip).toFixed(2)}</td>
                    </tr>
                    
                    `
            }
        },
        error: function (data, textstatus, jQxhr) {
            alert("No cuenta con transacciones realizadas")
        }

    })

}
)
