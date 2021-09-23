let interval;

function counter() {
	interval = setInterval(() => {
		const currentTime = moment(new Date()); // Creo que se puede hacer solo moment(); y ya funciona jaja
		const date = moment("fechaquetellegadelcontrolador");

		if (currentTime > date) {
			// Aca haces lo que quieras cuando la fecha actual es mayor a la que te llega del controlador
		}
		const dateDifference = moment.duration(date.diff(currentTime));
		const counterInfo = {
			days: dateDifference.days(),
			hours: dateDifference.hours(),
			minutes: dateDifference.minutes(),
			seconds: dateDifference.seconds()
		};
		// Acá podes mostrarlo por consola por ejemplo
		console.log(counterInfo.days, ' : ', counterInfo.hours, ' : ', counterInfo.minutes, ' : ', counterInfo.seconds);
		// O tambien lo metes en un tag de HTML, eso como te guste más
	}, 1000);
}

// Esta función es para eliminar el interval y que deje de consumir memoria
function cleanCounter() {
	clearInterval(interval);
}

// GUARDA: El contador solo llamalo una vez ya que es un interval, cada 1 segundo hace todo lo de la fecha
counter();