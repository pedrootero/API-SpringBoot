package APISpring.api.controller;

import APISpring.api.medico.DadosCadastroMedico;
import APISpring.api.medico.DadosListagemMedico;
import APISpring.api.medico.Medico;
import APISpring.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        //System.out.println(dados);
        repository.save(new Medico(dados));

    }
    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
        //return repository.findAll(paginacao).stream().map(DadosListagemMedico::new).toList(); //duvida
    }
}
