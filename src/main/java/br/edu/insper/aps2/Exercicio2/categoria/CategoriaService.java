package Exercicio2;

import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final LojaService lojaService;

    public CategoriaService(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    public Categoria criar(LojaService.CategoriaRequest request) { return lojaService.criarCategoria(request); }
}
