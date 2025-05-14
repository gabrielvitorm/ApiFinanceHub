package br.com.financehub.apifinancehub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String emailUsuario;

    @NotBlank(message = "Senha é obrigatória")
    private String senhaUsuario;

}
