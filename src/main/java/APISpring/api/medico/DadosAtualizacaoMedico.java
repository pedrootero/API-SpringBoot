package APISpring.api.medico;

import APISpring.api.endereco.DadosEndereco;
import APISpring.api.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        String nome,

        String telefone,
        DadosEndereco endereco) {
}
