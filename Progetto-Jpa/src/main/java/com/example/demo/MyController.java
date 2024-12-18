package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class MyController {

    @Autowired
    private ProdService prodService;

    @GetMapping("/private/form")
    public String getForm(Model model) {
        // Recupera la lista di tutti i prodotti
        ArrayList<mobile> lista = prodService.getAllProd();
        model.addAttribute("lista", lista);
        return "form";
    }

    @ResponseBody
    @PostMapping("/submit")
    public String gestisciForm(@RequestParam("nome") String nome,
                               @RequestParam("marca") String marca,
                               @RequestParam("prezzo") int prezzo,
                               @RequestParam("url") String url) {
        // Crea un nuovo prodotto
        prodService.createMobile(nome, marca, prezzo, url);
        return nome + " aggiunto con successo";
    }

    @ResponseBody
    @PostMapping("/update")
    public String updatePrezzo (@RequestParam("nome") String nome,
                                @RequestParam("prezzo") int prezzo) {
        // Aggiorna il prezzo del prodotto
        prodService.updatePrezzo(nome, prezzo);
        return nome + " aggiornato con successo";
    }

    @ResponseBody
    @PostMapping("/delete")
    public String delete(@RequestParam("nome") String nome) {
        // Elimina il prodotto dal database
        prodService.deleteProd(nome);
        return nome + " cancellato con successo";
    }

    @ResponseBody
    @PostMapping("/update-url")
    public String updateUrl(@RequestParam("nome") String nome,
                             @RequestParam("url") String nuovoUrl) {
        // Aggiorna l'URL del prodotto
        prodService.updateUrl(nome, nuovoUrl);
        return "URL di " + nome + " aggiornato con successo";
    }
}
