package com.igor.agendadortarefa.controller;

import com.igor.agendadortarefa.business.TarefasService;
import com.igor.agendadortarefa.business.dto.TarefasDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasControler {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){ // @DATE... faz a conversao das data para o padrao que o DB consegue ler
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaPorTarefasEmail(@RequestHeader("Authorization")String token){
        List<TarefasDTO> tarefas = tarefasService.buscaTarefasPorEmail( token);
        return ResponseEntity.ok(tarefas);
    }
}
