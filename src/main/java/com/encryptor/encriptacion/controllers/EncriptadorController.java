package com.encryptor.encriptacion.controllers;

import com.encryptor.encriptacion.repositories.entities.Credenciales;
import com.encryptor.encriptacion.services.EncriptadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EncriptadorController {
    @Autowired
    private EncriptadorService encriptadorService;
    private final Credenciales credenciales;

    public EncriptadorController(){
        this.credenciales = new Credenciales();
    }

    @GetMapping("/encriptador")
    public String Encriptar(Model model){
        // a312kj4k1j2n123n
        // wD3YDAUct0/C1buOZNaYNeO0Q51vfDyn
        // Jermayn14151
        model.addAttribute("credenciales", credenciales);
        return "index";
    }
    @PostMapping("/encriptar_credenciales")
    public String EncriptarCredenciales(Credenciales credenciales, RedirectAttributes redirectAttributes){
        if(this.validarCredencialesVacias(credenciales, redirectAttributes)){
            return "redirect:/encriptador";
        }
        try{
            String encriptado = encriptadorService.encriptar(credenciales.getClave(), credenciales.getCifrado());
            redirectAttributes.addFlashAttribute("mensaje_exitoso", "La palabra clave es: " +
                    credenciales.getClave() + " y se usó el cifrado: " + credenciales.getCifrado());

            redirectAttributes.addFlashAttribute("ejecucion_encriptacion", "La encriptación es: " + encriptado);
        }catch (org.jasypt.exceptions.EncryptionOperationNotPossibleException e){
            redirectAttributes.addFlashAttribute("error", "El cifrado ingresado es incorrecto. " +
                                                    "Por favor, verifique el cifrado e inténtelo de nuevo.");
        }
        return "redirect:/encriptador";
    }
    @PostMapping("/desencriptar_credenciales")
    public String DesencriptarCredenciales(Credenciales credenciales, RedirectAttributes redirectAttributes){
        if(this.validarCredencialesVacias(credenciales, redirectAttributes)){
            return "redirect:/encriptador";
        }
        try{
            String desencriptado = encriptadorService.desencriptar(credenciales.getClave(), credenciales.getCifrado());
            redirectAttributes.addFlashAttribute("mensaje_exitoso", "La palabra clave es: " +
                    credenciales.getClave() + " y se usó el cifrado: " + credenciales.getCifrado());
            redirectAttributes.addFlashAttribute("ejecucion_encriptacion", "La desencriptación es: " + desencriptado);

        }catch (org.jasypt.exceptions.EncryptionOperationNotPossibleException e){
            redirectAttributes.addFlashAttribute("error", "El cifrado ingresado es incorrecto. " +
                    "Por favor, verifique el cifrado e inténtelo de nuevo.");
        }
        return "redirect:/encriptador";
    }
    private boolean validarCredencialesVacias(Credenciales credenciales, RedirectAttributes redirectAttributes){
        if(credenciales.getCifrado().isEmpty() | credenciales.getClave().isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Ninguno de los campos puede estar vacío. Por favor, rellene los campos.");
            return true;
        }
        return false;
    }
}
