/**
 * 
 */
const data = {
  labels: ['Día 1', 'Día 2', 'Día 3', 'Día 4', 'Día 5', 'Día 6', 'Día 7'],
  datasets: [
    {
      label: 'Ideal',
      data: [120, 100, 80, 60, 40, 20, 0],
      fill: false,
      borderColor: 'rgb(0, 255, 0)',
      tension: 0.1
    },
    {
      label: 'Real',
      data: [120, 100, 85, 70, 20, 30, 10],
      fill: false,
      borderColor: 'rgb(255, 0, 0)',
      tension: 0.1
    }
  ]
};

const config = {
  type: 'line',
  data: data,
  options: {}
};
// grafica de burndownchart
var myChart = new Chart(
  document.getElementById('burndown-chart'),
  config
);

//arrastre de las tareas del tablero kanban

function allowDrop(event) {
  event.preventDefault();
}

function drag(event) {
  event.dataTransfer.setData("text", event.target.id);
}

function drop(event) {
  event.preventDefault();
  var data = event.dataTransfer.getData("text");
  event.target.appendChild(document.getElementById(data));
}

function deleteTask(taskId) {
  document.getElementById(taskId).remove();
}



var iniciarGrafica = function () {





	$.post("/usuario/login", { 'correo': email, 'contrasenia': password }, /*callback*/ function (fragmento) {
		$("#contenedor").replaceWith(fragmento);
	});
};

