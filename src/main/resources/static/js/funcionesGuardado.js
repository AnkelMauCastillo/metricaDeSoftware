
var iniciarSesion = function () {
	var email = $("#correo").val();
	var password = $("#contrasenia").val();
	$.post("/usuario/login", { 'email': email, 'contrasenia': password }, /*callback*/ function (fragmento) {
		$("#contenedor").replaceWith(fragmento);
	});
};

var registrarUsuario = function () {

	var nombre = $("#nombre").val();
	var correo = $("#email").val();
	var password = $("#contrasenia").val();
	var rol =$("#rol").val();
	// falta el curso 
	$.get("/usuario/registro", {
		'nombre': nombre,'email': correo, 'contrasenia': password, 'rol':rol},
		function (fragmento) {
			$("#contenedor").replaceWith(fragmento);
		});
};

$(document).ready(function () {


	jQuery.validator.addMethod("sololetrasyespacios", function (value, element) {
		return this.optional(element) || /^[a-z\s]+$/i.test(value);
	}, "Solo letras y espacios");


	jQuery.extend(jQuery.validator.messages, {
		required: "Este campo es obligatorio.",
		remote: "Por favor, rellena este campo.",
		email: "Por favor, escribe una dirección de correo válida",
		url: "Por favor, escribe una URL válida.",
		date: "Por favor, escribe una fecha válida.",
		dateISO: "Por favor, escribe una fecha (ISO) válida.",
		number: "Por favor, escribe un número entero válido.",
		digits: "Por favor, escribe sólo dígitos.",
		creditcard: "Por favor, escribe un número de tarjeta válido.",
		equalTo: "Por favor, escribe el mismo valor de nuevo.",
		accept: "Por favor, escribe un valor con una extensión aceptada.",
		maxlength: jQuery.validator.format("Por favor, no escribas más de {0} caracteres."),
		minlength: jQuery.validator.format("Por favor, no escribas menos de {0} caracteres."),
		rangelength: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1} caracteres."),
		range: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1}."),
		max: jQuery.validator.format("Por favor, escribe un valor menor o igual a {0}."),
		min: jQuery.validator.format("Por favor, escribe un valor mayor o igual a {0}.")
	});

	// ***************** Formularios ***************************************************

	


$("#registro-usuario").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			nombre: {
				required: true,
				maxlength: 100
				
			},
			correo: {
				required: true,
				email: true
			}
			
	
		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var nombre = $("#nombre").val();
			var correo = $("#correo").val();
			var rol = $("#rol").val();
			
			$.get("/usuario/registro", {'nombre': nombre, 'email': correo,  'rol':rol 
			}, function (fragmento) {



				$('#modalMensaje').replaceWith(fragmento);

				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();
               //Agregar controlador de eventos para la ventana modal
               $('#modalExitosoError').on('click', '#botonnAceptar', function() {
                // Seleccionar el formulario y restablecerlo
                 $('#registro-usuario')[0].reset();
                })
			});

			return false;
		}

	});
   	// registro de historia
	$("#registro-historia").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			titulo: {
				required: true,
				maxlength: 100
				
			},
			puntos: {
				required: true,
				number:true
			},
			
			asignado: {
				required: true
			},
			sprint:{
				required:true
			}
			
		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var titulo = $("#titulo").val();
			var descripcion = $("#descripcion").val();
			var puntos = $("#puntos").val();
			var estado=$("#estado").val();
		    var asignado=$("#asignado").val();
		    var sprint=$("#sprint").val();
			$.get("/historia/registro", {
				'title': titulo, 
				'description': descripcion, 'points': puntos,  'status':estado ,'asignado':asignado, 'sprint':sprint
			}, function (fragmento) {



				$('#modalMensaje').replaceWith(fragmento);

				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();
               //Agregar controlador de eventos para la ventana modal
               $('#modalExitosoError').on('click', '#botonAceptar', function() {
                // Seleccionar el formulario y restablecerlo
                 $('#registro-historia')[0].reset();
                })
			});

			return false;
		}

	});

getEstado = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}

getPuntos = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}

getAsignado = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}

getSprint = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}
getRol = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}

});


