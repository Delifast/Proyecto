package com.finalpry.restaurantwapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finalpry.restaurantwapp.model.Cliente;
import com.finalpry.restaurantwapp.repository.ClienteRepository;
import com.finalpry.restaurantwapp.util.ServiceSendMail;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    ServiceSendMail servicesendmail;
    
    
    private static final double[] TASAS = { 0.08, 0.14, 0.17, 0.2, 0.3 };
	
	
	private double round(double valor, int q_decimales) {
	    if (q_decimales < 0) return 0;

	    long factor = (long) Math.pow(10, q_decimales);
	    valor = valor * factor;
	    long tmp = Math.round(valor);
	    return (double) tmp / factor;
	}
	
	private Cliente calcularIR(Cliente cliente) {
		
		long uit = cliente.getUit();
		long[] ESCALA_UIT = { uit * 5, uit * 20, uit * 35, uit * 45 };
		double renta_neta_gravable = 0;
		
		
		double[] impuestos = { 0, 0, 0, 0, 0 };
		double[] monto_afecto = { 0, 0, 0, 0, 0 };
		double monto_afecto_total = 0;
		
		// BRUTO - TAXES = NETO
		double ingreso_bruto_anual = 0;
		double total_impuesto = 0;
		
		
		if (cliente.getRelacion_laboral().equalsIgnoreCase("Dependiente")) {

			ingreso_bruto_anual = cliente.getSalario() * 14;
		} else {

			ingreso_bruto_anual = cliente.getSalario() * 12;
		}
		//BL
		long monto_uit_descontable = uit * 7;

		renta_neta_gravable = ingreso_bruto_anual - monto_uit_descontable;

		if (renta_neta_gravable > 0) {

			if (renta_neta_gravable <= ESCALA_UIT[0]) {

				monto_afecto[0] = renta_neta_gravable;
				impuestos[0] = monto_afecto[0] * TASAS[0];

			} else if (renta_neta_gravable <= ESCALA_UIT[1]) {

				monto_afecto[0] = ESCALA_UIT[0];
				impuestos[0] = monto_afecto[0] * TASAS[0];

				monto_afecto[1] = renta_neta_gravable - monto_afecto[0];
				impuestos[1] = monto_afecto[1] * TASAS[1];

			} else if (renta_neta_gravable <= ESCALA_UIT[2]) {

				monto_afecto[0] = ESCALA_UIT[0];
				impuestos[0] = monto_afecto[0] * TASAS[0];

				monto_afecto[1] = ESCALA_UIT[1] - monto_afecto[0];
				impuestos[1] = monto_afecto[1] * TASAS[1];

				monto_afecto[2] = renta_neta_gravable - (monto_afecto[0] + monto_afecto[1]);
				impuestos[2] = monto_afecto[2] * TASAS[2];

			} else if (renta_neta_gravable <= ESCALA_UIT[3]) {
				
				monto_afecto[0] = ESCALA_UIT[0];
				impuestos[0] = monto_afecto[0] * TASAS[0];

				monto_afecto[1] = ESCALA_UIT[1] - monto_afecto[0];
				impuestos[1] = monto_afecto[1] * TASAS[1];

				monto_afecto[2] = ESCALA_UIT[2] - (monto_afecto[0] + monto_afecto[1]);
				impuestos[2] = monto_afecto[2] * TASAS[2];
				
				monto_afecto[3] = renta_neta_gravable - (monto_afecto[0] + monto_afecto[1] + monto_afecto[2]);
				impuestos[3] = monto_afecto[3] * TASAS[3];
				
			} else {
				
				monto_afecto[0] = ESCALA_UIT[0];
				impuestos[0] = monto_afecto[0] * TASAS[0];

				monto_afecto[1] = ESCALA_UIT[1] - monto_afecto[0];
				impuestos[1] = monto_afecto[1] * TASAS[1];

				monto_afecto[2] = ESCALA_UIT[2] - (monto_afecto[0] + monto_afecto[1]);
				impuestos[2] = monto_afecto[2] * TASAS[2];
				
				monto_afecto[3] = ESCALA_UIT[3] - (monto_afecto[0] + monto_afecto[1] + monto_afecto[2]);
				impuestos[3] = monto_afecto[3] * TASAS[3];
				
				monto_afecto[4] = renta_neta_gravable - (monto_afecto[0] + monto_afecto[1] + monto_afecto[2] + monto_afecto[3]);
				impuestos[4] = monto_afecto[4] * TASAS[4];
			}
			
			for (int i = 0; i < impuestos.length; i++) {
				total_impuesto += impuestos[i];
				monto_afecto_total += monto_afecto[i];
				
				System.out.println("monto ["+ i + "] -> " + monto_afecto[i] + "|| impuesto ["+ i + "] -> " +impuestos[i]);
			}
			
			cliente.setTotal_bruto(round(ingreso_bruto_anual, 2));
			cliente.setTotal_impuesto(round(total_impuesto, 2));
			cliente.setTotal_neto(round(ingreso_bruto_anual - total_impuesto, 2));
			
			System.out.println("suma de los montos " + monto_afecto_total);
			
		}else {
			cliente.setTotal_bruto(ingreso_bruto_anual);
			cliente.setTotal_impuesto(0);
			cliente.setTotal_neto(ingreso_bruto_anual);
		}
		
		return cliente;
	}

    @GetMapping("/clientes")
    public List<Cliente> getAllTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return clienteRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/clientes")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
    	cliente = calcularIR(cliente);
    	//servicesendmail.enviarCorreo(cliente);
        return clienteRepository.save(cliente);
    }

    @GetMapping(value="/clientes/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") String id) {
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok().body(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") String id,
                                           @Valid @RequestBody Cliente cliente) {
        return clienteRepository.findById(id)
                .map(clienteData -> {
                	clienteData.setDni(cliente.getDni());
                    clienteData.setNombre(cliente.getNombre());
                    clienteData.setApellidopat(cliente.getApellidopat());
                    clienteData.setApellidomat(cliente.getApellidomat());
                    clienteData.setCorreo(cliente.getCorreo());
                    Cliente updatedCliente = clienteRepository.save(clienteData);
                    return ResponseEntity.ok().body(updatedCliente);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") String id) {
        return clienteRepository.findById(id)
                .map(todo -> {
                    clienteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}