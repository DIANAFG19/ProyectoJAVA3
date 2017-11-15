
package org.unitec.elementos1801;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author YOO
 */
@RestController
@RequestMapping("/mensaje")
@CrossOrigin
public class ControladorMensajes {
    @Autowired RepositorioMensajito repoMensa; 
    //Aquí a continuación van las cinco operaciones básicas con la entidad mensaje.
    @RequestMapping(value="/{id}", method=RequestMethod.GET, headers = {"Accept=application/json"})
    
    public Mensajito obtenerMensajito(@PathVariable Integer id)throws Exception{
        return repoMensa.findOne(id);
    }
}