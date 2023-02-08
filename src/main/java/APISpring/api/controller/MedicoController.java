package APISpring.api.controller;

import APISpring.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        //return repository.findAll(paginacao).stream().map(DadosListagemMedico::new).toList(); //duvida
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }
    @DeleteMapping("/{id}") //parametro entre chaves quer dizer q é um parametro dinamico que pega o valor da url da rota
    @Transactional
    public void excluir(@PathVariable Long id){ //@PathVariable pega o parametro que vem da url
        //repository.deleteById(id); // exclui o cadastro do banco

        var medico = repository.getReferenceById(id); //apenas inativa o cadastro
        medico.excluir();
    }
}
