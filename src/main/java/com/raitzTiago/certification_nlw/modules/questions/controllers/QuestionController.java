package com.raitzTiago.certification_nlw.modules.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raitzTiago.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import com.raitzTiago.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.raitzTiago.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.raitzTiago.certification_nlw.modules.questions.entities.QuestionEntity;
import com.raitzTiago.certification_nlw.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDTO(question))
                .collect(Collectors.toList());

        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDto = QuestionResultDTO.builder()
                .technology(question.getTechnology())
                .id(question.getId())
                .description(question.getDescription()).build();

        List<AlternativesResultDTO> alternativeResultDTOs = question.getAlternatives()
                .stream().map(alternative -> mapAlternativeDTO(alternative))
                .collect(Collectors.toList());
        questionResultDto.setAlternatives(alternativeResultDTOs);
        return questionResultDto;
    }

    static AlternativesResultDTO mapAlternativeDTO(AlternativesEntity alternativesEntity) {
        return AlternativesResultDTO.builder()
                .id(alternativesEntity.getId())
                .description(alternativesEntity.getDescription()).build();

    }

}
