package com.example.taller.correo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CorreoController {
    @Autowired
    private  CorreoSevice correoSevice;

    @GetMapping("/mail")
    public String index()
    {
        return "correo";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("nombre")String nombre, @RequestParam("apellido")String apellido, @RequestParam("telefono")String telefono, @RequestParam("correo")String correoReceptor, @RequestParam("mensaje")String mensaje)
    {
        String correoRemitente = "brayanmezac.dev@gmail.com";
        String cuerpoCorreo = "Datos del Cliente:\n\n" +
                "Nombre: " + nombre + " " + apellido +
                "\nTelefono: " + telefono +
                "\nCorreo electronico: " + correoReceptor +
                "\n\nSolicitud:\n\n" + mensaje;
        try
        {
            correoSevice.sendMail(correoRemitente, correoReceptor, cuerpoCorreo);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "redirect:/";
    }
}
