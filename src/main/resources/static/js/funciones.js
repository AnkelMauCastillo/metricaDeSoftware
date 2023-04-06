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

// arrastre de tareas 
// Seleccionar todas las tareas
const tareas = document.querySelectorAll('.tarea');

// Añadir un manejador de eventos para el evento "dragstart"
tareas.forEach(tarea => {
  tarea.addEventListener('dragstart', dragstart_handler);
});

// Añadir un manejador de eventos para el evento "drop"
const columnas = document.querySelectorAll('.column');
columnas.forEach(column=> {
  column.addEventListener('drop', drop_handler);
});

// Función que se ejecuta al comenzar a arrastrar una tarea
function dragstart_handler(event) {
  // Agregar un identificador de datos para identificar la tarea
  event.dataTransfer.setData('text/plain', event.target.id);
}



// Función que se ejecuta al soltar una tarea en una columna
function drop_handler(event) {
  event.preventDefault();
  // Obtener el identificador de la tarea arrastrada
  const tarea_id = event.dataTransfer.getData('text/plain');
  // Mover la tarea a la nueva columna
  const tarea = document.getElementById(tarea_id);
  event.target.appendChild(tarea);
  // Guardar el nuevo estado de la tarea en el almacenamiento local
  localStorage.setItem(tarea_id, event.target.id);
}

// Restaurar el estado de las tareas desde el almacenamiento local al cargar la página
window.addEventListener('load', () => {
  const tareas = document.querySelectorAll('.tarea');
  tareas.forEach(tarea => {
    const estado = localStorage.getItem(tarea.id);
    if (estado) {
      const column = document.getElementById(estado);
      column.appendChild(tarea);
    }
  });
});

