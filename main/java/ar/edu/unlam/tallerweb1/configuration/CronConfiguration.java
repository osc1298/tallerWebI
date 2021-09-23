package ar.edu.unlam.tallerweb1.configuration;


import javax.inject.Inject;
import org.springframework.scheduling.annotation.Scheduled;
import ar.edu.unlam.tallerweb1.servicios.ServicioSubasta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPermutacion;

public class CronConfiguration {

	@Inject
	private ServicioPermutacion servicioPermutacion;
	
	@Inject
	private ServicioSubasta servicioSubasta;

	// @Scheduled(fixedDelay = 60000) Para que corra cada 60 segundos
	// @Scheduled(cron="0 0 0 * * ?") Cron para que corra todos los dias a medianoche
	
	//finaliza las permutaciones al llegar a la fecha de finalizacion
	@Scheduled(cron="0 0 0 * * ?")
	public void cronFinalizarPermutaciones() {
		servicioPermutacion.cronfinalizarPermutaciones();
	}
	
	//finaliza las subastas al llegar a la fecha de finalizacion verificando si hay ofertas ganadora
	@Scheduled(cron="0 0 0 * * ?")
	public void cronFinalizarSubastas() {
		servicioSubasta.cronfinalizarSubastas();
	}

}
