package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import org.springframework.stereotype.Service; 
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;
import java.util.Optional;




@Service
public class SetorService {
    
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor cadastrar(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listar() {
        return setorRepository.findAll();
    }

    public Optional<Setor> buscarPorId(Integer id) {
        return setorRepository.findById(id);
    }

    
    public Optional<Setor> atualizar(Integer id, Setor novosDados) {

        Optional<Setor> setorExistente = setorRepository.findById(id.intValue());

        if (setorExistente.isEmpty()) {
           return Optional.empty();
        }

        Setor setor = setorExistente.get();

        setor.setNome(novosDados.getNome());
        return Optional.of(setorRepository.save(setor));
    }

    public boolean excluir(Integer id) {

        if (!setorRepository.existsById(id.intValue())) {
            return false;
        }
        setorRepository.deleteById(id.intValue());
        return true;
    }


}
