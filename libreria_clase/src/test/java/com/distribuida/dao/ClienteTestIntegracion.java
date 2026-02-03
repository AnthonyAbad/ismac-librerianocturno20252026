package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ClienteTestIntegracion {

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    public void testClienteFindAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size()> 0);
        for (Cliente item: clientes){
            System.out.println(item.toString());
        }
    }

    @Test
    public void testClienteFindOne(){
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertNotNull(cliente.isPresent());
        assertEquals("Puro", cliente.orElse(null).getNombre());
        assertEquals("Hueso", cliente.orElse(null).getApellido());
        System.out.println(cliente);
    }

    @Test
    public void testClienteSave(){
        Cliente cliente = new Cliente(0, "123445","Pedro77", "Abad77", "Direccion77", "0998766554", "pedro77@gmail.com");
        Cliente clienteGuardado = clienteRepository.save(cliente);
        assertNotNull(clienteGuardado);
        assertEquals("123445", clienteGuardado.getCedula());
        assertEquals("Pedro77", clienteGuardado.getNombre());
    }

    @Test
    public void testClienteActualizar(){
        Optional<Cliente> cliente2 =clienteRepository.findById(39);

        cliente2.orElse(null).setCedula("8776654443");
        cliente2.orElse(null).setNombre("Pedro88");
        cliente2.orElse(null).setApellido("Abad88");
        cliente2.orElse(null).setDireccion("Direccion88");
        cliente2.orElse(null).setTelefono("09988888");
        cliente2.orElse(null).setCorreo("pedro8888@gmail.com");

        clienteRepository.save(cliente2.orElse(null));
    }

    @Test
    public void testClienteBorrar(){
        clienteRepository.deleteById(39);
    }
}
