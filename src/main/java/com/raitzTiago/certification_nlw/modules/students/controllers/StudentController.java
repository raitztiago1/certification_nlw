package com.raitzTiago.certification_nlw.modules.students.controllers;

import com.raitzTiago.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.raitzTiago.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.raitzTiago.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.raitzTiago.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.raitzTiago.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        // Email
        // Technology
        var result = this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
        if (result)
            return "Usuário já fez a prova!!!";

        return "usuario pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public CertificationStudentEntity certificationAnswer(
            @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        return this.studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
    }

}
